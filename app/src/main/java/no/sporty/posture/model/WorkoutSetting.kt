package no.sporty.posture.model

data class WorkoutSetting(
    val timeBasedTime: Int = 1, //Minutes
    val setRepBasedReps: Int = 4, // Repetitions
    val workoutSettingOption: WorkoutSettingOption = WorkoutSettingOption.TIME_BASED

)

enum class WorkoutSettingOption(text: String) {
    TIME_BASED("Time based"), SET_REP_BASED("Set / Rep based")
}