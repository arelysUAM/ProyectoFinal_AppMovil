package ni.edu.uam.appuam.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ni.edu.uam.appuam.screens.AvatarScreen
import ni.edu.uam.appuam.screens.HabitsScreen
import ni.edu.uam.appuam.screens.HomeScreen
import ni.edu.uam.appuam.screens.LoginScreen
import ni.edu.uam.appuam.screens.TipsScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // Pantalla Login
        composable("login") {
            LoginScreen(navController)
        }

        // Home con argumento username
        composable("home/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            HomeScreen(navController, username)
        }

        composable("habits") {
            HabitsScreen(navController)
        }

        composable("avatar") {
            AvatarScreen(navController)
        }

        composable("tips") {
            TipsScreen(navController)
        }
    }
}