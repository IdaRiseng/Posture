package no.sporty.posture.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import no.sporty.posture.R


// taken from this youtube video https://www.youtube.com/watch?v=eLfIsFl1Cac
enum class Movement(@StringRes val title: Int, @DrawableRes val illustration: Int, @RawRes val video: Int) {
    CHEST_OPENER(R.string.chest_opener, R.drawable.launcher_icon, R.raw.test),
    WALL_SLIDE(R.string.wall_slide, R.drawable.launcher_icon, R.raw.test),
    TABLE_TOP_LIFT(R.string.table_top_lift, R.drawable.launcher_icon, R.raw.test),
    GLUTE_BRIDGE(R.string.glute_bridge, R.drawable.launcher_icon, R.raw.test),
    ALL_FOUR_TWIST(R.string.all_four_twist, R.drawable.launcher_icon, R.raw.test),
    WALL_STRETCH(R.string.wall_stretch, R.drawable.launcher_icon, R.raw.test),
    SQUAT(R.string.squat, R.drawable.launcher_icon, R.raw.test),
}
