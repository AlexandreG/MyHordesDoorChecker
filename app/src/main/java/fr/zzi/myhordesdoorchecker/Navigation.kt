package fr.zzi.myhordesdoorchecker

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WebAsset
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import fr.zzi.myhordesdoorchecker.door.ui.DoorTab
import fr.zzi.myhordesdoorchecker.door.ui.DoorViewModel
import fr.zzi.myhordesdoorchecker.links.LinksTab
import fr.zzi.myhordesdoorchecker.settings.ui.SettingsTab
import fr.zzi.myhordesdoorchecker.settings.ui.SettingsViewModel

enum class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    Home("home", Icons.Default.Home, "Home"),
    Search("links", Icons.Default.WebAsset, "Links"),
    Profile("settings", Icons.Default.Settings, "Settings");
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    doorViewModel: DoorViewModel,
    settingsViewModel: SettingsViewModel
) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            DoorTab(doorViewModel)
        }
        composable(BottomNavItem.Search.route) {
            LinksTab()
        }
        composable(BottomNavItem.Profile.route) {
            SettingsTab(settingsViewModel)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavItem.entries.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.label) }
            )
        }
    }
}