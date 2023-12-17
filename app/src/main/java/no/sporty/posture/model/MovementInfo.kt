package no.sporty.posture.model

import androidx.annotation.DrawableRes

data class MovementInfo(
    @DrawableRes val illustration: Int,
    val whatToDo: String,
    val whatNotToDo: String
)