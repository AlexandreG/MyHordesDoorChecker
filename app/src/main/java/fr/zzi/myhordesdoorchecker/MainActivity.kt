package fr.zzi.myhordesdoorchecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import fr.zzi.myhordesdoorchecker.door.ui.DoorViewModel
import fr.zzi.myhordesdoorchecker.settings.ui.SettingsViewModel
import fr.zzi.myhordesdoorchecker.theme.AppTheme

class MainActivity : ComponentActivity() {

    private val doorViewModel by lazy { ViewModelProvider(this)[DoorViewModel::class.java] }
    private val settingsViewModel by lazy { ViewModelProvider(this)[SettingsViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainActivityUI()
        }
    }

    @Composable
    fun MainActivityUI() {
        AppTheme {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavigationHost(
                            navController = navController,
                            doorViewModel,
                            settingsViewModel
                        )
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainActivityUI()
    }
}
