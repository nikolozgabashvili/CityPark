package ge.tbca.city_park.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import ge.tbca.city_park.presentation.theme.AppColors
import ge.tbca.city_park.presentation.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), contentColor = AppColors.error, containerColor = AppColors.error) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        Surface(modifier = Modifier.fillMaxWidth().weight(1f)) {  }
                        Box(modifier=Modifier.fillMaxWidth().weight(1f))
                        Card(modifier = Modifier.fillMaxWidth().weight(1f)){}

                        OutlinedTextField(
                            label = { Text("hehehhe")},
                            value = TextFieldValue(),
                            onValueChange = {}
                        )

                    }

                }
            }
        }
    }
}
