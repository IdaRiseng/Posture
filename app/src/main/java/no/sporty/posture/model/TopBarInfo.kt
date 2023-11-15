package no.sporty.posture.model

import java.time.LocalDate

data class TopBarInfo(
    val streak: Int = 0,
    val total: Int = 0,
    val datesExercised: List<LocalDate> = emptyList()
)