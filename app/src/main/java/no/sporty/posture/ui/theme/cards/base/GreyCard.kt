import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import no.sporty.posture.extensions.conditional

@Composable
fun GreyCard(
    onClick: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null,
    padding: PaddingValues = PaddingValues(bottom = 16.dp),
    content: @Composable () -> Unit
) =
    Card(
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(padding)
            .fillMaxWidth()
            .conditional(onClick != null) { clickable { onClick?.let { it() } } },
        content = {
            Box {
                content()
                onDismiss?.let {
                    DismissButton(modifier = Modifier.align(Alignment.TopEnd)) { it() }
                }
            }
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        )
    )

@Composable
fun DismissButton(modifier: Modifier, onDismiss: () -> Unit) {
    IconButton(
        modifier = modifier,
        onClick = onDismiss,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
    ) {
        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
    }
}