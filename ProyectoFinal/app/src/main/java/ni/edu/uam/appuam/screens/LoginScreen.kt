package ni.edu.uam.appuam.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ni.edu.uam.appuam.R

@Composable
fun LoginScreen(navController: NavController) {

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var usernameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.fondologin),
            contentDescription = "Fondo de inicio",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(70.dp))

            Text(
                text = "HabitU",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF214832)
            )

            Text(
                text = "Construye mejores hábitos,\ncada día.",
                fontSize = 17.sp,
                color = Color(0xFF2E7D4F),
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.jaguar),
                contentDescription = "Mascota HabitU",
                modifier = Modifier.height(260.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.96f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                            usernameError = it.any { char -> char.isDigit() }
                        },
                        label = { Text("Nombre de usuario") },
                        placeholder = { Text("Ingresa tu nombre") },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = "Usuario")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        singleLine = true,
                        isError = usernameError,
                        supportingText = {
                            if (usernameError) {
                                Text("El nombre no puede contener números")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            val correo = it.trim().lowercase()
                            email = correo
                            emailError = correo.isNotBlank() && !correo.endsWith("@uamv.edu.ni")
                        },
                        label = { Text("Correo Gmail") },
                        placeholder = { Text("ejemplo@gmail.com") },
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = "Correo")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        singleLine = true,
                        isError = emailError,
                        supportingText = {
                            if (emailError) {
                                Text("Debe ingresar un correo Gmail válido")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            val correo = email.trim().lowercase()

                            usernameError = username.isBlank() || username.any { it.isDigit() }
                            emailError = correo.isBlank() || !correo.endsWith("@gmail.com")

                            if (!usernameError && !emailError) {
                                navController.navigate("home/$username")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2E7D4F)
                        )
                    ) {
                        Text(
                            text = "Entrar",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}