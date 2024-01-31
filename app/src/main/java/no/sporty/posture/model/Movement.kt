package no.sporty.posture.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import no.sporty.posture.R


enum class Movement(@StringRes val title: Int, @DrawableRes val illustration: Int, @RawRes val video: Int, val info: MovementInfo?) {

    //Wall
    WALL_SLIDE(R.string.wall_slide, R.drawable.wall_slide, R.raw.wall_slide, MovementInfo(null, R.string.wall_slide_desc)),

    //Sitting
    CHEST_OPENER(R.string.chest_opener, R.drawable.chest_opener, R.raw.chest_opener, MovementInfo(illustration = null, R.string.chest_opener_desc)),
    NECK_STRETCH(R.string.neck_stretch, R.drawable.neck_stretch, R.raw.neck_stretch, MovementInfo(illustration = null, R.string.neck_stretch_desc)),
    HEART_OPENER(R.string.heart_opener, R.drawable.heart_opener, R.raw.heart_opener, MovementInfo(illustration = null, R.string.heart_opener_desc)),
    LOWER_BACK_STRETCH(R.string.lower_back_stretch, R.drawable.lower_back_stretch, R.raw.lower_back_stretch, null),
    BACK_STRETCH(R.string.back_stretch, R.drawable.back_stretch, R.raw.back_strech, null),
    TURTLE_NECK(R.string.turtle_neck, R.drawable.turtle_neck, R.raw.turtle_neck, null),
    ANGEL_WINGS(R.string.angel_wings, R.drawable.angel_wings, R.raw.angel_wings, null),
    SHOULDER_CIRCLES(R.string.shoulder_circles, R.drawable.shoulder_circles, R.raw.shoulder_circles, null),

    //Laying
    TABLE_TOP_LIFT(R.string.table_top_lift, R.drawable.table_top_lift, R.raw.table_top_lift, MovementInfo(illustration = null, R.string.table_top_lift_desc)),
    ALL_FOUR_TWIST(R.string.all_four_twist, R.drawable.all_four_twist, R.raw.all_four_twist, MovementInfo(illustration = null, R.string.all_four_twist_desc)),
    GLUTE_BRIDGE(R.string.glute_bridge, R.drawable.glute_bridge, R.raw.glute_bridge, MovementInfo(illustration = null, R.string.glute_bridge_desc)),
    BACK_SQUEEZE_PULSE(R.string.back_squeeze_pulse, R.drawable.back_squeeze_pulse, R.raw.back_squeeze_pulse, MovementInfo(illustration = null, R.string.back_squeeze_pulse_desc)),
    BACK_SWIM(R.string.back_swim, R.drawable.back_swim, R.raw.back_swim, null),
    CAT_COW(R.string.cat_cow, R.drawable.cat_cow, R.raw.cat_cow, null),
    THORACIC_ROTATION(R.string.thoracic_rotation, R.drawable.thoracic_rotation, R.raw.thoracic_rotation, null),
    EXTENDED_PUPPYPOSE(R.string.extended_puppy_pose, R.drawable.extended_puppypose, R.raw.extended_puppypose, null),
    LOTUS_POSE(R.string.lotus_pose, R.drawable.lotus_pose, R.raw.lotus_pose, null),

    YWTS(R.string.y_w_t_s, R.drawable.y_w_t, R.raw.y_w_t, null),
    SHOULDER_DISLOCATIONS(R.string.shoulder_dislocations, R.drawable.shoulder_disloactions, R.raw.shoulder_dislocations, null),
}
