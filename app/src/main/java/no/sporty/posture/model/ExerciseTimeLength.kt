package no.sporty.posture.model

enum class ExerciseTimeLength(val length: Int){
    TWO_MINUTES( 2),
    FIVE_MINUTES(5),
    TEN_MINUTES(10);
}

enum class ExerciseRepetitionsLength(val length: Int){
    TWO_REPS( 2),
    FOUR_REPS(5),
    EIGHT_REPS(8);
}