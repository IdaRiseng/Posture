package no.sporty.posture.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import no.sporty.posture.R


enum class Movement(@StringRes val title: Int, @DrawableRes val illustration: Int, @RawRes val video: Int, info: MovementInfo?) {
    CHEST_OPENER(R.string.chest_opener, R.drawable.launcher_icon, R.raw.test, null),
    WALL_SLIDE(R.string.wall_slide, R.drawable.launcher_icon, R.raw.test, null),
    TABLE_TOP_LIFT(R.string.table_top_lift, R.drawable.launcher_icon, R.raw.test, null),
    GLUTE_BRIDGE(R.string.glute_bridge, R.drawable.launcher_icon, R.raw.test, null),
    ALL_FOUR_TWIST(R.string.all_four_twist, R.drawable.launcher_icon, R.raw.test, null),
    WALL_STRETCH(R.string.wall_stretch, R.drawable.launcher_icon, R.raw.test, null),
    SQUAT(R.string.squat, R.drawable.launcher_icon, R.raw.test, null),
}
