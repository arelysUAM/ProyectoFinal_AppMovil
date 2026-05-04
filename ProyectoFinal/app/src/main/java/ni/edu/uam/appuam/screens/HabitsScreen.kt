package ni.edu.uam.appuam.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ni.edu.uam.appuam.model.Habit
import ni.edu.uam.appuam.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitsScreen(navController: NavController) {

    // Estado
    var habits = remember { mutableStateListOf<Habit>() }
    var newHabit by remember { mutableStateOf("") }

    // Scaffold para UI moderna con topbar
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Hábitos") },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text("← Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = VerdePrincipal,
                    titleContentColor = Blanco
                )
            )
        },
        containerColor = Fondo
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            // 🔹 INPUT + BOTÓN
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Blanco),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    OutlinedTextField(
                        value = newHabit,
                        onValueChange = { newHabit = it },
                        label = { Text("Nuevo hábito") },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            if (newHabit.isNotEmpty()) {
                                habits.add(Habit(newHabit))
                                newHabit = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = VerdePrincipal
                        )
                    ) {
                        Text("+")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 LISTA DE HÁBITOS
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(habits) { habit ->

                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = VerdeCard
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = habit.name,
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Checkbox(
                                checked = habit.completed,
                                onCheckedChange = { habit.completed = it }
                            )
                        }
                    }
                }
            }
        }
    }
}