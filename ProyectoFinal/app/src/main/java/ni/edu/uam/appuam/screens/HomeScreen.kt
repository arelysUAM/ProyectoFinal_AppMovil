package ni.edu.uam.appuam.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import ni.edu.uam.appuam.ui.theme.Blanco
import ni.edu.uam.appuam.ui.theme.Fondo
import ni.edu.uam.appuam.ui.theme.GrisMedio
import ni.edu.uam.appuam.ui.theme.GrisOscuro
import ni.edu.uam.appuam.ui.theme.MoradoCard
import ni.edu.uam.appuam.ui.theme.MoradoOscuro
import ni.edu.uam.appuam.ui.theme.VerdeCard
import ni.edu.uam.appuam.ui.theme.VerdeClaro
import ni.edu.uam.appuam.ui.theme.VerdeOscuro
import ni.edu.uam.appuam.ui.theme.VerdePrincipal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    username: String
) {
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    var lastBackPressedTime by remember {
        mutableLongStateOf(0L)
    }

    BackHandler {
        val currentTime = System.currentTimeMillis()

        if (currentTime - lastBackPressedTime < 2000) {
            (context as? android.app.Activity)?.finish()
        } else {
            lastBackPressedTime = currentTime
            Toast.makeText(
                context,
                "Presiona nuevamente para salir de la app",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            HomeSideMenu(
                onCloseDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Fondo,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Inicio",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = VerdeOscuro
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Abrir menú",
                                tint = GrisOscuro
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                // Botón decorativo por ahora
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.NotificationsNone,
                                contentDescription = "Notificaciones",
                                tint = GrisOscuro
                            )
                        }
                    }
                )
            },
            bottomBar = {
                HomeBottomBar(
                    onHomeClick = {
                        // Ya estamos en Inicio
                    },
                    onGamingClick = {
                        // En desarrollo
                    },
                    onQuizzesClick = {
                        // En desarrollo
                    },
                    onScoreClick = {
                        // En desarrollo
                    }
                )
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "¡Hola, $username! 👋",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = VerdeOscuro
                )

                Text(
                    text = "¿Lista para tener un día productivo?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = GrisMedio
                )

                Spacer(modifier = Modifier.height(20.dp))

                DailyTipCard()

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Explora HabitU",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = GrisOscuro
                )

                Spacer(modifier = Modifier.height(12.dp))

                HomeOptionCard(
                    title = "Mis hábitos",
                    description = "Agrega y completa tus hábitos diarios.",
                    icon = Icons.Default.TaskAlt,
                    iconTint = VerdeOscuro,
                    backgroundColor = VerdeCard,
                    onClick = {
                        navController.navigate("habits")
                    }
                )

                Spacer(modifier = Modifier.height(14.dp))

                HomeOptionCard(
                    title = "Mi avatar",
                    description = "Personaliza tu jaguar y cambia su estado.",
                    icon = Icons.Default.Pets,
                    iconTint = MoradoOscuro,
                    backgroundColor = MoradoCard,
                    onClick = {
                        navController.navigate("avatar")
                    }
                )

                Spacer(modifier = Modifier.height(14.dp))

                HomeOptionCard(
                    title = "Tips ecológicos",
                    description = "Aprende consejos para cuidar el planeta.",
                    icon = Icons.Default.Eco,
                    iconTint = VerdeOscuro,
                    backgroundColor = Color(0xFFE3F2FD),
                    onClick = {
                        navController.navigate("tips")
                    }
                )
            }
        }
    }
}

@Composable
fun HomeSideMenu(
    onCloseDrawer: () -> Unit
) {
    ModalDrawerSheet(
        modifier = Modifier
            .width(280.dp)
            .fillMaxHeight(),
        drawerContainerColor = Blanco
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Menú",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = VerdeOscuro
                )

                IconButton(
                    onClick = onCloseDrawer
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Cerrar menú",
                        tint = GrisOscuro
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            SideMenuItem(
                title = "Perfil",
                icon = Icons.Default.Person,
                onClick = {
                    // En desarrollo
                }
            )

            SideMenuItem(
                title = "Configuraciones",
                icon = Icons.Default.Settings,
                onClick = {
                    // En desarrollo
                }
            )

            SideMenuItem(
                title = "Cerrar sesión",
                icon = Icons.Default.PowerSettingsNew,
                onClick = {
                    // En desarrollo
                }
            )
        }
    }
}

@Composable
fun SideMenuItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = VerdePrincipal,
            modifier = Modifier.size(26.dp)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            color = GrisOscuro,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun HomeBottomBar(
    onHomeClick: () -> Unit,
    onGamingClick: () -> Unit,
    onQuizzesClick: () -> Unit,
    onScoreClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        shape = RoundedCornerShape(28.dp),
        color = Blanco,
        shadowElevation = 6.dp,
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                label = "Inicio",
                icon = Icons.Default.Home,
                selected = true,
                onClick = onHomeClick
            )

            BottomBarItem(
                label = "Gaming",
                icon = Icons.Default.Extension,
                selected = false,
                onClick = onGamingClick
            )

            BottomBarItem(
                label = "Quizzes",
                icon = Icons.Default.Quiz,
                selected = false,
                onClick = onQuizzesClick
            )

            BottomBarItem(
                label = "Score",
                icon = Icons.Default.EmojiEvents,
                selected = false,
                onClick = onScoreClick
            )
        }
    }
}

@Composable
fun BottomBarItem(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    val itemColor = if (selected) VerdePrincipal else GrisMedio

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = itemColor,
            modifier = Modifier.size(26.dp)
        )

        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = itemColor
        )
    }
}

