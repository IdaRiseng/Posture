package no.sporty.posture.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MovementInfo(
    @DrawableRes val illustration: Int,
    @StringRes val desc: Int,
)