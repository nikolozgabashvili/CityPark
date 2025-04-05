package ge.tbca.city_park.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ge.tbca.city_park.presentation.ui.design_system.components.button.ButtonSize
import ge.tbca.city_park.presentation.ui.design_system.components.button.PrimaryButton
import ge.tbca.city_park.presentation.ui.design_system.components.button.TertiaryButton
import ge.tbca.city_park.presentation.ui.design_system.components.text_field.PasswordTextField
import ge.tbca.city_park.presentation.ui.design_system.components.text_field.TextInputField
import ge.tbca.city_park.presentation.ui.theme.AppTheme
import ge.tbca.city_park.presentation.ui.theme.Dimen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(

                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding)
                            .imePadding(),
                        verticalArrangement = Arrangement.spacedBy(Dimen.size24)
                    ) {

                        var password by remember { mutableStateOf("") }
                        var passwordVisible by remember { mutableStateOf(false) }
                        var input by remember { mutableStateOf("") }
                        var loading by remember { mutableStateOf(false) }
                        var error: String? by remember { mutableStateOf(null) }
                        TextInputField(
                            modifier = Modifier.fillMaxWidth(),
                            value = input,
                            label = "label",
                            onTextChanged = {
                                input = it
                            }
                        )

                        PasswordTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = password,
                            errorText = error,
                            isPasswordVisible = passwordVisible,
                            label = "label",
                            onTextChanged = {
                                password = it
                                error = null
                            },
                            onToggleTextVisibility = {
                                println(passwordVisible)
                                passwordVisible = !passwordVisible
                            }
                        )

                        PrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Loading",
                            onClick = {
                                loading = !loading
                            }
                        )

                        TertiaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Tertiary",
                            buttonSize = ButtonSize.LARGE,
                            loading = loading,
                            onClick = {
                                error = "hehhehehe"
                            }
                        )

                    }

                }
            }
        }
    }
}
