package no.sporty.posture.model

import androidx.annotation.DrawableRes
import no.sporty.posture.R

enum class Exercise(
    val title: String,
    val shortDesc: String,
    @DrawableRes val illustration: Int,
    val movements: List<Movement>
) {
    BUILD_AND_STRETCH(
        title = "Build and stretch",
        shortDesc = "Strengthen your back muscles, stretch your front muscles",
        illustration = R.drawable.launcher_icon,
        movements = listOf(
            Movement.YWTS,
            Movement.PRAYER_STRETCH,
            Movement.SCARECROW,
            Movement.SHOULDER_DISLOCATIONS,
            Movement.WALL_SLIDE,
            Movement.QUADRUPED_REACH,
            Movement.DOOR_FRAME_STRETCH
        )
    ),
    SIMPLE_OFFICE_EXERCISE(
        title = "Simple office exercise",
        shortDesc = "Exercises while sitting on a chair",
        illustration = R.drawable.launcher_icon,
        movements = listOf(
            Movement.CHEST_OPENER,
            Movement.NECK_STRETCH,
            Movement.HEART_OPENER,
            Movement.LOWER_BACK_STRETCH,
            Movement.BACK_STRETCH,
            Movement.TURTLE_NECK,
            Movement.ANGEL_WINGS,
            Movement.SHOULDER_CIRCLES
        )
    ),
    MINDFUL_STRETCHING(
        title = "Mindful stretching",
        shortDesc = "Slow down and relax any tension, remember your breathing",
        illustration = R.drawable.launcher_icon,
        movements = listOf(
            Movement.ANGEL_WINGS,
            Movement.SHOULDER_CIRCLES,
            Movement.NECK_STRETCH,
            Movement.CAT_COW,
            Movement.THORACIC_ROTATION,
            Movement.EXTENDED_PUPPYPOSE,
            Movement.LOTUS_POSE,
            Movement.SHOULDER_STRETCH,
            Movement.DYNAMIC_CAMEL,
            Movement.TABLE_TOP_LIFT
        )
    ),
    BED_EXERCISE(
        title = "Bed exercises",
        shortDesc = "Do exercises while laying in bed",
        illustration = R.drawable.launcher_icon,
        movements = listOf(
            Movement.YWTS,
            Movement.AB_STRETCH,
            Movement.BACK_SQUEEZE_PULSE,
            Movement.BACK_SWIM,
            Movement.LOTUS_POSE,
            Movement.SHOULDER_STRETCH
        )
    ),
    AT_THE_GYM(
        title = "At the gym",
        shortDesc = "Use the tools at your gym for a better workout",
        illustration = R.drawable.launcher_icon,
        movements = listOf(
            Movement.IDK,
            Movement.IDK_TWO,
            Movement.IDK_THREE,
            Movement.SHOULDER_DISLOCATIONS_FOAM_ROLLER,
            Movement.QUADRUPED_REACH,
            Movement.SHOULDER_STRETCH,
            Movement.PULL_APARTS,
            Movement.PRAYER_STRETCH,
            Movement.SCARECROW,
            Movement.SHOULDER_DISLOCATIONS,
        )
    )
}
