package com.example.maplescouter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.maplescouter.ui.DetailScreen
import com.example.maplescouter.ui.MainScreen
import com.example.maplescouter.ui.theme.MapleScouterTheme
import com.example.maplescouter.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MapleScouterTheme {
                MapleScouterApp(viewModel)
            }
        }
    }
}

@Composable
fun MapleScouterApp(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val state by viewModel.characterListState.collectAsState()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                state = state,
                onCharacterClick = { ocid ->
                    navController.navigate("detail/$ocid")
                }
            )
        }
        composable(
            route = "detail/{ocid}",
            arguments = listOf(navArgument("ocid") { type = NavType.StringType })
        ) { backStackEntry ->
            val ocid = backStackEntry.arguments?.getString("ocid")
            DetailScreen(
                ocid = ocid,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
