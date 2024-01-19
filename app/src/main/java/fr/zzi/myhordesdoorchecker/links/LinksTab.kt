package fr.zzi.myhordesdoorchecker.links

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LinksTab() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Liens",
            style = MaterialTheme.typography.displayLarge
        )

    }

}
