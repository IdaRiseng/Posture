package no.sporty.posture.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectedMovements(
    val movements: List<Movement>
) : Parcelable {
    companion object {
        val TAG = SelectedMovements::class.java.simpleName
    }
}