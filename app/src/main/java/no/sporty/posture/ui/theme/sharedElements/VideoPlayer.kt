package no.sporty.posture.ui.theme.sharedElements

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.util.Log
import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player.REPEAT_MODE_ALL
import com.google.android.exoplayer2.ui.StyledPlayerView


@Composable
fun VideoPlayer(@RawRes res: Int) {
    val context = LocalContext.current
    val mediaItem = MediaItem.Builder()
        .setUri(getResourceUri(context, res))
        .build()
    val exoPlayer = remember(context, mediaItem) {
        ExoPlayer.Builder(context)
            .build()
            .also { exoPlayer ->
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
                exoPlayer.volume = 0f
                exoPlayer.playWhenReady = true
                exoPlayer.repeatMode = REPEAT_MODE_ALL
            }
    }

    DisposableEffect(
        Box(Modifier.background(MaterialTheme.colorScheme.secondary).fillMaxWidth(), contentAlignment = Alignment.Center) {
            AndroidView(factory = {
                StyledPlayerView(context).apply {
                    player = exoPlayer
                }
            })
        }
    ) {
        onDispose { exoPlayer.release() }
    }
}

private fun getResourceUri(context: Context, @RawRes res: Int): Uri? {
    return try {
        Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                    + context.resources.getResourcePackageName(res) + '/'
                    + context.resources.getResourceTypeName(res) + '/'
                    + context.resources.getResourceEntryName(res)
        )
    } catch (e: Resources.NotFoundException) {
        Log.w("resource_parsing", "Received invalid resource id: $res", e)
        null
    }
}