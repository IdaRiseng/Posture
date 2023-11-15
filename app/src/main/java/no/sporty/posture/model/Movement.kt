package no.sporty.posture.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import no.sporty.posture.R


// taken from this youtube video https://www.youtube.com/watch?v=eLfIsFl1Cac
enum class Movement(@StringRes val title: Int, @DrawableRes val illustration: Int, @DrawableRes val video: Int, val durability: Long) {
    CHEST_OPENER(R.string.chest_opener, R.drawable.launcher_icon, R.drawable.launcher_icon, 5),
    WALL_SLIDE(R.string.wall_slide, R.drawable.launcher_icon, R.drawable.launcher_icon, 5),
    TABLE_TOP_LIFT(R.string.table_top_lift, R.drawable.launcher_icon, R.drawable.launcher_icon, 5),
    GLUTE_BRIDGE(R.string.glute_bridge, R.drawable.launcher_icon, R.drawable.launcher_icon, 5),
    ALL_FOUR_TWIST(R.string.all_four_twist, R.drawable.launcher_icon, R.drawable.launcher_icon, 5),
    WALL_STRETCH(R.string.wall_stretch, R.drawable.launcher_icon, R.drawable.launcher_icon, 5),
    SQUAT(R.string.squat, R.drawable.launcher_icon, R.drawable.launcher_icon, 5),
}
