package no.sporty.posture.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class CustomExercise(
    val title: String,
    val desc: String,
    val movements: List<Movement>,
    @DrawableRes val illustration: Int,
): Parcelable {
    companion object{
        val TAG = CustomExercise::class.java.simpleName
    }
}