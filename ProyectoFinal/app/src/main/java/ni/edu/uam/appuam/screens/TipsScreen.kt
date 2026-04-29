package ni.edu.uam.appuam.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ni.edu.uam.appuam.ui.theme.Fondo
import ni.edu.uam.appuam.ui.theme.GrisMedio
import ni.edu.uam.appuam.ui.theme.VerdeOscuro
import ni.edu.uam.appuam.ui.theme.VerdePrincipal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipsScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Fondo,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tips ecológicos",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = VerdeOscuro
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar",
                            tint = VerdeOscuro
                        )
                    }
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Eco,
                        contentDescription = "Tips ecológicos",
                        tint = VerdePrincipal,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            )
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Column {
                    Text(
                        text = "Consejos para cuidar el planeta",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = VerdeOscuro
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Pequeñas acciones que puedes aplicar en tu día a día.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = GrisMedio
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            items(consejosHabitU) { consejo ->
                TipCard(
                    consejo = consejo
                )
            }
        }
    }
}