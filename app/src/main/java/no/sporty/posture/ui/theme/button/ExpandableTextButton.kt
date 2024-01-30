package no.sporty.posture.ui.theme.button

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import no.sporty.posture.sharedPreferences.AffirmationPref
import no.sporty.posture.ui.theme.sharedElements.isDarkmode
import no.sporty.posture.ui.theme.text.TitleBlackText

@Composable
fun ExpandableTextButton(
    @StringRes textRes: Int,
    @DrawableRes iconId: Int,
    isSelected: Boolean? = null,
    showCheckBox: Boolean = false,
    showSwitch: Boolean = false,
    onClick: () -> Unit,
    content: @Composable (() -> Unit)? = null
) {
    //for Switch
    val context = LocalContext.current
    var enabled by remember { mutableStateOf(AffirmationPref.getAffirmationEnabled(context)) }


    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(26.dp))
            .clickable {
                AffirmationPref.saveAffirmationEnabled(context, enabled)
                enabled = !enabled
                onClick()
            }
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(16.dp)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconId), modifier = Modifier
                    .size(38.dp)
                    .padding(end = 16.dp), contentDescription = stringResource(id = textRes), tint = if (isDarkmode()) Color.White else Color.Black
            )
            TitleBlackText(textRes = textRes)
            when {
                showCheckBox -> {
                    Box(Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                        RadioButton(selected = isSelected == true, onClick = onClick)
                    }
                }

                showSwitch -> {
                    Box(Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                        Switch(checked = enabled, onCheckedChange = {
                            onClick()
                            enabled = !enabled
                            AffirmationPref.saveAffirmationEnabled(context, enabled)
                        })
                    }
                }
            }

        }

        content?.let {
            AnimatedVisibility(visible = isSelected == true) {
                Column {
                    Spacer(modifier = Modifier.padding(8.dp))
                    it()
                }
            }
        }
    }
}