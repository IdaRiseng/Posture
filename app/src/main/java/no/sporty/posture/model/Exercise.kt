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
        illustration = R.drawable.front_image_build_and_stretch,
        movements = listOf(
            Movement.YWTS,
            Movement.SHOULDER_DISLOCATIONS,
            Movement.ANGEL_WINGS,
            Movement.HEART_OPENER,
            Movement.TURTLE_NECK,
            Movement.BACK_SQUEEZE_PULSE
        )
    ),
    SIMPLE_OFFICE_EXERCISE(
        title = R.string.simple_office_exercise,
        shortDesc = R.string.simple_office_exercise_desc,
        illustration = R.drawable.front_image_office_workout,
        movements = listOf(
            Movement.CHEST_OPENER,
            Movement.NECK_STRETCH,
            Movement.HEART_OPENER,
            Movement.LOWER_BACK_STRETCH,
            Movement.BACK_STRETCH,
            Movement.TURTLE_NECK,
            Movement.ANGEL_WINGS,
            Movement.SHOULDER_CIRCLES,
        )
    ),
    MINDFUL_STRETCHING(
        title = R.string.mindful_stretching,
        shortDesc = R.string.mindful_stretching_desc,
        illustration = R.drawable.front_image_stretch,
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
        illustration = R.drawable.front_image_bed,
        movements = listOf(
            Movement.YWTS,
            Movement.CAT_COW,
            Movement.ALL_FOUR_TWIST,
            Movement.GLUTE_BRIDGE,
            Movement.THORACIC_ROTATION,
            Movement.BACK_SQUEEZE_PULSE,
            Movement.BACK_SWIM,
            Movement.LOTUS_POSE,
            Movement.TABLE_TOP_LIFT,
            Movement.EXTENDED_PUPPYPOSE
        )
    )
}
