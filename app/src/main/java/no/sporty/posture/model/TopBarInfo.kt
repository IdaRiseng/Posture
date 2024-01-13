package no.sporty.posture.model

import no.sporty.posture.sharedPreferences.SavedExerciseInfo

data class TopBarInfo(
    val streak: Int = 0,
    val total: Int = 0,
    val datesExercised: List<SavedExerciseInfo> = emptyList()
)