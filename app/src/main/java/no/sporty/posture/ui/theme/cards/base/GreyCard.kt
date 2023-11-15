import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import no.sporty.posture.extensions.conditional

@Composable
fun GreyCard(onClick: (() -> Unit)? = null, content: @Composable ColumnScope.() -> Unit) =
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(bottom = 8.dp)
            .conditional(onClick != null) { clickable { onClick?.let { it() } } }
            .fillMaxWidth(),
        content = content,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )