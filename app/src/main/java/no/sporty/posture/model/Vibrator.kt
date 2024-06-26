package no.sporty.posture.model

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator


object PostureVibrator {
    fun vibrate(context: Context) {
        val v = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v?.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            v?.vibrate(100)
        }
    }
}