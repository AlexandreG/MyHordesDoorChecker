package fr.zzi.myhordesdoorchecker.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingsTab(settingsViewModel: SettingsViewModel) {
    val username = settingsViewModel.getUsername().observeAsState("").value
    val userkey = settingsViewModel.getUserkey().observeAsState("").value
    Column(modifier = Modifier.padding(16.dp)) {
        SettingsTitle()
        ConnectedStatus(username)
        UserKeyRow(userkey) { text ->
            settingsViewModel.onSendUserkey(text)
        }
    }
}

@Composable
fun SettingsTitle() {
    Text(
        text = "Paramètres",
        style = MaterialTheme.typography.displayLarge,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun ConnectedStatus(username: String?) {
    if (username.isNullOrBlank()) {
        Text("Veuillez entrer votre userkey")
    } else {
        Text("Connecté : $username")
    }
}

@Composable
fun UserKeyRow(userkey: String?, onTextFilled: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 16.dp)
    ) {
        var text by remember { mutableStateOf(TextFieldValue(userkey ?: "")) }
        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            modifier = Modifier.weight(1.0f),
            label = { Text("ID externe pour les apps") }
        )
        Button(
            onClick = {
                onTextFilled.invoke(text.text)
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = "OK")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val settingsViewModel = remember { SettingsViewModel() }
    SettingsTab(settingsViewModel)
}
