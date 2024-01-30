package no.sporty.posture.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import no.sporty.posture.R

enum class Exercise(
    @StringRes val title: Int,
    @StringRes val shortDesc: Int,
    @DrawableRes val illustration: Int,
    val movements: List<Movement>
) {
    BUILD_AND_STRETCH(
        title = R.string.build_and_stretch,
        shortDesc = R.string.build_and_stretch_desc,
        illustration = R.drawable.wall_slide,
        movements = listOf(
            Movement.YWTS,
            Movement.SHOULDER_DISLOCATIONS,
            Movement.WALL_SLIDE,
        )
    ),
    SIMPLE_OFFICE_EXERCISE(
        title = R.string.simple_office_exercise,
        shortDesc = R.string.simple_office_exercise_desc,
        illustration = R.drawable.lower_back_stretch,
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
        title = R.string.mindful_stretching,
        shortDesc = R.string.mindful_stretching_desc,
        illustration = R.drawable.shoulder_circles,
        movements = listOf(
            Movement.ANGEL_WINGS,
            Movement.SHOULDER_CIRCLES,
            Movement.NECK_STRETCH,
            Movement.CAT_COW,
            Movement.THORACIC_ROTATION,
            Movement.EXTENDED_PUPPYPOSE,
            Movement.LOTUS_POSE,
            Movement.TABLE_TOP_LIFT
        )
    ),
    BED_EXERCISE(
        title = R.string.bed_exercise,
        shortDesc = R.string.bed_exercise_desc,
        illustration = R.drawable.glute_bridge,
        movements = listOf(
            Movement.YWTS,
            Movement.CAT_COW,
            Movement.GLUTE_BRIDGE,
            Movement.BACK_SQUEEZE_PULSE,
            Movement.BACK_SWIM,
            Movement.LOTUS_POSE,
            Movement.EXTENDED_PUPPYPOSE
        )
    ),
    /*AT_THE_GYM(
        title = R.string.at_the_gym,
        shortDesc = R.string.at_the_gym_desc,
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
    )*/
}
