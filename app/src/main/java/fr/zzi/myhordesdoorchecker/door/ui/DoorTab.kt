package fr.zzi.myhordesdoorchecker.door.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun DoorTab(doorViewModel: DoorViewModel) {

    val doorOpen = doorViewModel.areDoorOpen()
    val lastCheckTime = doorViewModel.getLastCheckTime()
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Door Checker",
            style = MaterialTheme.typography.displayLarge
        )


        Text(
            text = "Cette appli n'est pas affiliée à Motion Twin ou à MyHordes. Certains éléments graphiques appartiennent à Motion Twin ou à MyHordes ",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Les portes sont actuellement ",
            style = MaterialTheme.typography.headlineMedium
        )

        if (doorOpen == null) {
            Button(
                onClick = {}
            ) {
                Text(text = "Clickez pour vérifier")
            }
        } else if (doorOpen) {
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

    }

}
