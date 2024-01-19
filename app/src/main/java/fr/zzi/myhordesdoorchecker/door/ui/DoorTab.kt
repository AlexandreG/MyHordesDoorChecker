package fr.zzi.myhordesdoorchecker.door.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun DoorTab(doorViewModel: DoorViewModel) {

    val userkeyFilled = doorViewModel.isUserkeyFilled().observeAsState().value
    val doorOpen = doorViewModel.areDoorOpen().observeAsState().value
    val lastCheckTime = doorViewModel.getLastCheckTime().observeAsState().value

    Column {
        Text(
            text = "Position de la porte",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(16.dp)
        )

        if (userkeyFilled == true) {
            DoorDetails(doorOpen, lastCheckTime) {
                doorViewModel.onCheckDoorStatus()
            }
        } else {
            Text(
                text = "Veuillez rentrer votre ID dans l'onglet Paramètres ",
                style = MaterialTheme.typography.headlineMedium,

                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1.0f))

        Text(
            text = "Cette appli n'est pas affiliée à Motion Twin ou à MyHordes. Certains éléments graphiques appartiennent à Motion Twin ou à MyHordes ",
            color = Color.White,
            modifier = Modifier
                .background(Color.LightGray)
                .align(alignment = Alignment.CenterHorizontally)
                .padding(8.dp),
            style = MaterialTheme.typography.bodyMedium
        )

    }

}

@Composable
fun DoorDetails(doorOpen: Boolean?, lastCheckTime: Long?, onButtonClick: () -> Unit) {
    Column {
        Text(
            text = "Les portes sont actuellement ...",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp)
        )

//        if (doorOpen == null) {
//            Button(
//                onClick = { onButtonClick.invoke() },
//                modifier = Modifier
//                    .padding(32.dp)
//                    .align(alignment = Alignment.CenterHorizontally)
//            ) {
//                Text(text = "Clickez pour vérifier")
//            }
//        } else {
        val textSting =
            if (doorOpen == null) "CLIQUER POUR VERIFIER" else if (doorOpen) "OUVERTES" else "FERMÉES"
        val textColor =
            if (doorOpen == null) Color.DarkGray else if (doorOpen) Color.Green else Color.Red
        Text(
            text = textSting,
            color = textColor,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(48.dp)
                .clickable {
                    onButtonClick.invoke()
                }
        )
//        }

        val formattedTime =
            SimpleDateFormat("MMM dd yyyy HH:mm:ss", Locale.getDefault()).format(lastCheckTime)
        Text(
            text = "Dernière vérification le : $formattedTime",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DoorDetailsPreview() {
    DoorDetails(true, System.currentTimeMillis() - 3000) {}
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val doorViewModel = remember { DoorViewModel() }
    DoorTab(doorViewModel)
}
