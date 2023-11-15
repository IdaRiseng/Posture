import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
private fun BigHeadlineText(text: String, color: Color, padding: PaddingValues = PaddingValues()) {
    Text(
        text = text,
        fontSize = 42.sp,
        color = color,
        modifier = Modifier.padding(padding)
    )
}


@Composable
fun BigHeadlineAlwaysWhiteText(@StringRes textRes: Int) = BigHeadlineText(text = stringResource(id = textRes), color = Color.White)

@Composable
fun BigHeadlineAlwaysWhiteText(text: String) = BigHeadlineText(text = text, color = Color.White)


@Composable
fun BigHeadlineBlackText(text: String) = BigHeadlineText(text = text, color = MaterialTheme.colorScheme.onBackground)
