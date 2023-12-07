package no.sporty.posture.activities.mainView.elements

import BigHeadlineAlwaysWhiteText
import SmallDisabledWhiteText
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import no.sporty.posture.R
import no.sporty.posture.ui.theme.sharedElements.PostureWave
import no.sporty.posture.model.TopBarInfo
import no.sporty.posture.ui.theme.text.BodyAlwaysWhiteText
import no.sporty.posture.ui.theme.text.HeadlineAlwaysWhiteText
import java.time.LocalDate


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainTopBar(topBarInfo: TopBarInfo, onSettingsClicked: () -> Unit) {
    Box(Modifier.background(MaterialTheme.colorScheme.primary)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val pagerState = rememberPagerState()
            HorizontalPager(state = pagerState, count = 3, modifier = Modifier.padding(bottom = 16.dp)) { page ->
                when (page) {
                    0 -> Streak(topBarInfo.streak)
                    1 -> Total(topBarInfo.total)
                    2 -> Calendar(topBarInfo.datesExercised)
                }
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color.White,
                inactiveColor = Color.White.copy(ContentAlpha.disabled),
                modifier = Modifier.padding(bottom = 32.dp)
            )
            PostureWave()
        }
        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp), onClick = onSettingsClicked
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_profile_settings),
                modifier = Modifier.size(32.dp),
                contentDescription = null,
                tint = Color.White
            )
        }

    }
}


@Composable
private fun Streak(streak: Int) {
    PagerView {
        CountView(title = R.string.streak, count = streak)
    }
}

@Composable
private fun Total(total: Int) {
    PagerView {
        CountView(title = R.string.total, count = total)
    }
}

@Composable
private fun Calendar(datesExercised: List<LocalDate>) {
    PagerView {
        CustomCalendar(datesExercised)
    }
}

@Composable
private fun PagerView(content: @Composable ColumnScope.() -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(280.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Composable
private fun CountView(@StringRes title: Int, count: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(50.dp))
        HeadlineAlwaysWhiteText(text = stringResource(id = title))
        Spacer(modifier = Modifier.height(16.dp))
        BigHeadlineAlwaysWhiteText(text = count.toString())
        Spacer(modifier = Modifier.height(16.dp))
        BodyAlwaysWhiteText(text = pluralStringResource(id = R.plurals.day, count))
        Spacer(modifier = Modifier.height(50.dp))
        SmallDisabledWhiteText(text = stringArrayResource(id = R.array.simple_affirmations).random())
    }
}