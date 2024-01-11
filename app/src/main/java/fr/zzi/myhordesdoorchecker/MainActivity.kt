package fr.zzi.myhordesdoorchecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.zzi.myhordesdoorchecker.theme.AppTheme
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(null, System.currentTimeMillis())
                }
            }
        }
    }

    @Composable
    fun HomeScreen(open: Boolean?, lastCheckTime: Long) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Door Checker",
                style = MaterialTheme.typography.displayLarge
            )

            Text(
                text = "Les portes sont actuellement ",
                style = MaterialTheme.typography.headlineMedium
            )

            if (open == null) {
                Button(
                    onClick = {}
                ) {
                    Text(text = "Clickez pour vérifier")
                }
            } else if (open) {
                Text(
                    text = "OUVERTES",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                Text(
                    text = "FERMÉES",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            val time1 =
                SimpleDateFormat("MMM dd yyyy HH:mm:ss", Locale.getDefault()).format(lastCheckTime)
            Text(
                text = "Dernière vérification le : $time1",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.weight(1.0f))

            Text(
                text = "Cette appli n'est pas affiliée à Motion Twin ou à MyHordes. Certains éléments graphiques appartiennent à Motion Twin ou à MyHordes ",
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        AppTheme {
            HomeScreen(null, System.currentTimeMillis())
        }
    }
}