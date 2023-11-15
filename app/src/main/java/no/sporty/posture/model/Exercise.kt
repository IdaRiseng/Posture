package no.sporty.posture.model

import androidx.annotation.DrawableRes
import no.sporty.posture.R

enum class Exercise(
    val title: String,
    val shortDesc: String,
    val longDesc: String,
    @DrawableRes val illustration: Int,
    val movements: List<Movement>
) {
    BUILD_AND_STRETCH(
        title = "Build and stretch",
        shortDesc = "Strengthen your back muscles",
        longDesc = "When having a bad posture, your front muscles is alot stronger than your back muscles. Stretch your front muscles and build your back muscle!",
        illustration = R.drawable.launcher_icon,
        movements = listOf(
            Movement.CHEST_OPENER,
            Movement.WALL_SLIDE,
            Movement.TABLE_TOP_LIFT,
            Movement.GLUTE_BRIDGE,
            Movement.ALL_FOUR_TWIST
        )
    ),
    OFFICE_EXERCISE(
        title = "Office exercise",
        shortDesc = "Strengthen your back muscles",
        longDesc = "When having a bad posture, your front muscles is alot stronger than your back muscles. Stretch your front muscles and build your back muscle!",
        illustration = R.drawable.launcher_icon,
        movements = listOf(
            Movement.CHEST_OPENER,
            Movement.WALL_SLIDE,
        )
    ),
    LAYING_EXERCISE(
        title = "Laying exercise",
        shortDesc = "Strengthen your back muscles",
        longDesc = "When having a bad posture, your front muscles is alot stronger than your back muscles. Stretch your front muscles and build your back muscle!",
        illustration = R.drawable.launcher_icon,
        movements = listOf(
            Movement.CHEST_OPENER,
            Movement.WALL_SLIDE,
        )
    )
}
