package ni.edu.uam.appuam.model

// Modelo simple para hábitos
data class Habit(
    val name: String,
    var completed: Boolean = false
)