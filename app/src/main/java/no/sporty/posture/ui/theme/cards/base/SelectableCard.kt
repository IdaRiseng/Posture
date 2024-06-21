package no.sporty.posture.ui.theme.cards.base

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

@Composable
fun SelectableCard(isSelected: Boolean, onSelected: () -> Unit, content: @Composable ColumnScope.() -> Unit) =
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(bottom = 8.dp)
            .clickable { onSelected() }
            .fillMaxWidth(),
        content = content,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.tertiary
        )
    )