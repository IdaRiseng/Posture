package no.sporty.posture.ui.theme.cards

import GreyCard
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.sporty.posture.R
import no.sporty.posture.ui.theme.PostureTheme
import no.sporty.posture.ui.theme.button.PrimaryButton
import no.sporty.posture.ui.theme.text.BodyBlackText
import no.sporty.posture.ui.theme.text.HeadlineBlackText

@Preview
@Composable
fun PreviewInfoCard() {
    PostureTheme {
        Column {
            InfoCard(
                title = R.string.info_thank_you_title,
                desc = R.string.info_thank_you_desc,
                icon = R.drawable.launcher_icon_round
            )
            Spacer(modifier = Modifier.height(16.dp))
            InfoCard(
                title = R.string.info_thank_you_title,
                desc = R.string.info_thank_you_desc,
            )
            Spacer(modifier = Modifier.height(16.dp))
            InfoCard(
                title = R.string.info_thank_you_title,
                desc = R.string.info_thank_you_desc,
                buttonInfo = ButtonInfo({}, R.string._continue)
            )
            Spacer(modifier = Modifier.height(16.dp))
            InfoCard(
                title = R.string.info_thank_you_title,
                desc = R.string.info_thank_you_desc,
                buttonInfo = ButtonInfo({}, R.string._continue),
                image = R.drawable.launcher_icon
            )
        }
    }
}

data class ButtonInfo(
    val onClick: () -> Unit,
    @StringRes val text: Int
)

@Composable
fun InfoCard(
    @StringRes title: Int,
    @StringRes desc: Int,
    buttonInfo: ButtonInfo? = null,
    @DrawableRes image: Int? = null,
    @DrawableRes icon: Int? = null,
) {
    val localDensity = LocalDensity.current
    var columnHeightDp by remember { mutableStateOf(0.dp) }

    GreyCard {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    columnHeightDp = with(localDensity) { coordinates.size.height.toDp() }
                },
        ) {
            icon?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                        .size(36.dp)
                )
            }

            Column(
                Modifier
                    .padding(16.dp)
                    .weight(3f)
            ) {
                HeadlineBlackText(text = stringResource(id = title))
                Spacer(modifier = Modifier.height(8.dp))
                BodyBlackText(text = stringResource(id = desc))
                buttonInfo?.let {
                    Spacer(modifier = Modifier.height(16.dp))
                    PrimaryButton(onClick = it.onClick, textRes = it.text)
                }
            }

            image?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier
                        .height(columnHeightDp)
                        .fillMaxWidth()
                        .weight(1.5f), // TODO gotta do something about this
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}



