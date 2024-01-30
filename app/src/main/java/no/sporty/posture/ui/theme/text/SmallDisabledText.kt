import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
private fun SmallDisabledText(text: String, color: Color, textAlign: TextAlign? = null) =
    Text(
        text = text,
        textAlign = textAlign,
        fontSize = 14.sp,
        color = color,
        fontStyle = FontStyle.Italic
    )

@Composable
fun SmallDisabledBlackText(@StringRes textRes: Int) = SmallDisabledText(text = stringResource(id = textRes), color = MaterialTheme.colorScheme.onBackground)

@Composable
fun SmallDisabledBlackText(text: String) = SmallDisabledText(text = text, color = MaterialTheme.colorScheme.onBackground)

@Composable
fun SmallDisabledWhiteText(text: String, textAlign: TextAlign? = null) = SmallDisabledText(text = text, color = Color.White, textAlign)