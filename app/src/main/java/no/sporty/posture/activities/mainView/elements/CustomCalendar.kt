package no.sporty.posture.activities.mainView.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import no.sporty.posture.R
import no.sporty.posture.model.Exercise
import no.sporty.posture.sharedPreferences.CustomExercisePrefs
import no.sporty.posture.sharedPreferences.SavedExerciseInfo
import no.sporty.posture.ui.theme.sharedElements.isDarkmode
import no.sporty.posture.ui.theme.text.BodyBlackText
import no.sporty.posture.ui.theme.text.HeadlineAlwaysWhiteText
import no.sporty.posture.ui.theme.text.HeadlineBlackText
import no.sporty.posture.ui.theme.text.TitleAlwaysWhiteText
import no.sporty.posture.ui.theme.text.TitleBlackText
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCalendar(
    datesExercised: List<SavedExerciseInfo>
) {
    var selectedDate by remember { mutableStateOf(SavedExerciseInfo(null, LocalDate.now())) }
    var displayedMonth by remember { mutableStateOf(LocalDate.of(selectedDate.date.year, selectedDate.date.month, 1)) }
    val openBottomSheet = rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutine = rememberCoroutineScope()

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {
                displayedMonth = displayedMonth.minus(1, ChronoUnit.MONTHS)
            }
        ) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, tint = Color.White, contentDescription = null)
        }

        CalendarGrid(
            modifier = Modifier.weight(3f),
            displayedMonth = displayedMonth,
            selectedDate = selectedDate,
            datesExercised = datesExercised,
            onDateSelected = { newDate ->
                selectedDate = datesExercised.find { it.date == newDate } ?: SavedExerciseInfo(null, newDate)
                coroutine.launch {
                    openBottomSheet.value = true
                    bottomSheetState.expand()
                }
            }
        )

        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {
                displayedMonth = displayedMonth.plus(1, ChronoUnit.MONTHS)
            }
        ) {
            Icon(imageVector = Icons.Default.KeyboardArrowRight, tint = Color.White, contentDescription = null)
        }
    }
    CalendarBottomSheet(selectedDate, openBottomSheet, bottomSheetState)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalendarBottomSheet(savedExerciseInfo: SavedExerciseInfo, openBottomSheet: MutableState<Boolean>, bottomSheetState: SheetState) {
    if (openBottomSheet.value && savedExerciseInfo.name != null) {
        val context = LocalContext.current

        ModalBottomSheet(sheetState = bottomSheetState, onDismissRequest = { openBottomSheet.value = false }) {
            val pattern = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)

            Column(Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 58.dp)) {
                CustomExercisePrefs.getCustomExerciseList(context).find { it.title == savedExerciseInfo.name }?.let {
                    BodyBlackText(text = stringResource(R.string.you_did_this_custom_exercise, savedExerciseInfo.date.format(pattern)))
                    HeadlineBlackText(text = it.title)
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = it.illustration),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
                Exercise.values().find { stringResource(it.title) == savedExerciseInfo.name }?.let {
                    BodyBlackText(text = stringResource(R.string.you_did_this_exercise, savedExerciseInfo.date.format(pattern)))
                    HeadlineBlackText(text = stringResource(it.title))
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = it.illustration),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
    }
}


@Composable
fun CalendarGrid(
    modifier: Modifier,
    displayedMonth: LocalDate,
    selectedDate: SavedExerciseInfo,
    datesExercised: List<SavedExerciseInfo>,
    onDateSelected: (LocalDate) -> Unit,
) {
    val dateFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.getDefault())
    val dateString = dateFormatter.format(displayedMonth)
    val weekdays = stringArrayResource(id = R.array.weekdays)

    Column(modifier) {
        HeadlineAlwaysWhiteText(text = dateString.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.padding(top = 8.dp),
            userScrollEnabled = false,
            content = {
                items(weekdays) { day ->
                    TitleAlwaysWhiteText(
                        text = day,
                        padding = PaddingValues(4.dp),
                    )
                }

                for (i in 1 until displayedMonth.dayOfWeek.value) {
                    // Fill empty cells before the first day of the month
                    item {
                        Spacer(modifier = Modifier.fillMaxWidth())
                    }
                }

                for (day in getDaysInCurrentMonth(displayedMonth)) {
                    val isSelected = selectedDate.date == day
                    val exerciseDates = datesExercised.map { it.date }
                    val hasDoneExercise = exerciseDates.contains(day)

                    item {
                        CalendarDay(
                            day = day,
                            isSelected = isSelected,
                            onDateSelected = onDateSelected,
                            hasDoneExercise = hasDoneExercise
                        )
                    }
                }
            }
        )
    }
}

fun getDaysInCurrentMonth(selectedDate: LocalDate): List<LocalDate> {
    val yearMonth = YearMonth.of(selectedDate.year, selectedDate.month)
    val daysInMonth = yearMonth.lengthOfMonth()
    val firstDay = selectedDate.withDayOfMonth(1)

    val daysList = ArrayList<LocalDate>()
    for (day in 0 until daysInMonth) {
        val date = firstDay.plusDays(day.toLong())
        daysList.add(date)
    }

    return daysList
}

@Composable
fun CalendarDay(
    day: LocalDate,
    isSelected: Boolean,
    hasDoneExercise: Boolean,
    onDateSelected: (LocalDate) -> Unit
) {
    Box(
        modifier = Modifier
            .size(35.dp)
            .padding(4.dp)
            .clip(CircleShape)
            .background(
                when {
                    isSelected -> Color.White
                    hasDoneExercise -> MaterialTheme.colorScheme.secondary
                    else -> Color.Transparent
                }
            )
            .clickable {
                onDateSelected(day)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = DateTimeFormatter.ofPattern("d", Locale.getDefault()).format(day),
            textAlign = TextAlign.Center,
            color = when {
                isSelected -> MaterialTheme.colorScheme.primary
                isDarkmode() && hasDoneExercise -> Color.Black
                else -> Color.White
            }
        )
    }
}



