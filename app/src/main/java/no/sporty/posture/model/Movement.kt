package no.sporty.posture.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import no.sporty.posture.R


enum class Movement(@StringRes val title: Int, @DrawableRes val illustration: Int, @RawRes val video: Int, val info: MovementInfo?) {

    //Wall
    WALL_SLIDE(R.string.wall_slide, R.drawable.launcher_icon, R.raw.test, MovementInfo(R.drawable.launcher_icon, R.string.wall_slide_desc)),
    WALL_TOUCH(R.string.wall_touch, R.drawable.launcher_icon, R.raw.test, null),
    DOOR_FRAME_STRETCH(R.string.door_frame_stretch, R.drawable.launcher_icon, R.raw.test, null),

    //Sitting
    CHEST_OPENER(R.string.chest_opener, R.drawable.launcher_icon, R.raw.test, null),
    NECK_STRETCH(R.string.neck_stretch, R.drawable.launcher_icon, R.raw.test, null),
    HEART_OPENER(R.string.heart_opener, R.drawable.launcher_icon, R.raw.test, null),
    LOWER_BACK_STRETCH(R.string.lower_back_stretch, R.drawable.launcher_icon, R.raw.test, null),
    BACK_STRETCH(R.string.back_stretch, R.drawable.launcher_icon, R.raw.test, null),
    TURTLE_NECK(R.string.turtle_neck, R.drawable.launcher_icon, R.raw.test, null),
    ANGEL_WINGS(R.string.angel_wings, R.drawable.launcher_icon, R.raw.test, null),
    SHOULDER_CIRCLES(R.string.shoulder_circles, R.drawable.launcher_icon, R.raw.test, null),

    //Laying
    TABLE_TOP_LIFT(R.string.table_top_lift, R.drawable.launcher_icon, R.raw.test, null),
    GLUTE_BRIDGE(R.string.glute_bridge, R.drawable.launcher_icon, R.raw.test, null),
    AB_STRETCH(R.string.ab_stretch, R.drawable.launcher_icon, R.raw.test, null),
    DOWN_DOG_TWIST(R.string.down_dog_twist, R.drawable.launcher_icon, R.raw.test, null),
    BACK_SQUEEZE_PULSE(R.string.back_squeeze_pulse, R.drawable.launcher_icon, R.raw.test, null),
    BACK_SWIM(R.string.back_swim, R.drawable.launcher_icon, R.raw.test, null),
    CAT_COW(R.string.cat_cow, R.drawable.launcher_icon, R.raw.test, null),
    THORACIC_ROTATION(R.string.thoracic_rotation, R.drawable.launcher_icon, R.raw.test, null),
    EXTENDED_PUPPYPOSE(R.string.extended_puppy_pose, R.drawable.launcher_icon, R.raw.test, null),
    LOTUS_POSE(R.string.lotus_pose, R.drawable.launcher_icon, R.raw.test, null),
    SHOULDER_STRETCH(R.string.shoulder_stretch, R.drawable.launcher_icon, R.raw.test, null),
    DYNAMIC_CAMEL(R.string.dynamic_camel, R.drawable.launcher_icon, R.raw.test, null),
    YWTS(R.string.y_w_t_s, R.drawable.launcher_icon, R.raw.test,  MovementInfo(R.drawable.launcher_icon, R.string.wall_slide_desc)),


    //Gym
    IDK(R.string.idk, R.drawable.launcher_icon, R.raw.test, null),
    IDK_TWO(R.string.idk_two, R.drawable.launcher_icon, R.raw.test, null),
    IDK_THREE(R.string.idk_three, R.drawable.launcher_icon, R.raw.test, null),
    SHOULDER_DISLOCATIONS_FOAM_ROLLER(R.string.shoulder_dislocation_foam_roller, R.drawable.launcher_icon, R.raw.test, null),
    QUADRUPED_REACH(R.string.quadruped_reach, R.drawable.launcher_icon, R.raw.test, null),
    PULL_APARTS(R.string.pull_aparts, R.drawable.launcher_icon, R.raw.test, null),
    PRAYER_STRETCH(R.string.prayer_stretch, R.drawable.launcher_icon, R.raw.test, null),
    SCARECROW(R.string.scarecrow, R.drawable.launcher_icon, R.raw.test, null),
    SHOULDER_DISLOCATIONS(R.string.shoulder_dislocations, R.drawable.launcher_icon, R.raw.test, null),
}
