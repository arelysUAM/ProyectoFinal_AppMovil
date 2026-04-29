package ni.edu.uam.appuam.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ni.edu.uam.appuam.R

@Composable
fun AvatarScreen(navController: NavController) {

    var estado by remember { mutableStateOf("study") }

    val imagen = if (estado == "study") {
        R.drawable.sprite_estudiando
    } else {
        R.drawable.sprite_pijama
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Volver")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Tu Avatar", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = imagen),
            contentDescription = "Avatar",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { estado = "study" }) {
            Text("Modo Estudio")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { estado = "rest" }) {
            Text("Modo Descanso")
        }
    }
}