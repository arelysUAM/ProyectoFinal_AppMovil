package ni.edu.uam.appuam.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ni.edu.uam.appuam.model.Habit

@Composable
fun HabitsScreen(navController: NavController) {

    var habits = remember { mutableStateListOf<Habit>() }
    var newHabit by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp).statusBarsPadding()) {
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Volver")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Mis Hábitos", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.statusBarsPadding())

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newHabit,
            onValueChange = { newHabit = it },
            label = { Text("Nuevo hábito") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (newHabit.isNotEmpty()) {
                habits.add(Habit(newHabit))
                newHabit = ""
            }
        }) {
            Text("Agregar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(habits) { habit ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(habit.name)

                    Checkbox(
                        checked = habit.completed,
                        onCheckedChange = { habit.completed = it }
                    )
                }
            }
        }
    }
}