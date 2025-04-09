package ge.tbca.city_park.presentation.features.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tbca.city_park.presentation.features.auth.screen.login.LoginScreenRoot
import ge.tbca.city_park.presentation.features.auth.screen.register.RegisterScreenRoot
import kotlinx.serialization.Serializable


fun NavGraphBuilder.authNavGraph(
    onShowSnackBar: suspend (String) -> Unit,
    navigateToHome: () -> Unit,
    navigateToRegister: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    navigateBack: () -> Unit
) {
    composable<LoginScreenRoute> {
        LoginScreenRoot(
            onShowSnackBar = onShowSnackBar,
            navigateToHome = navigateToHome,
            navigateToRegister = navigateToRegister,
            navigateToForgotPassword = navigateToForgotPassword,
        )

    }

    composable<RegisterScreenRoute> {
        RegisterScreenRoot(
            onShowSnackBar = onShowSnackBar,
            navigateToHome = navigateToHome,
            navigateBack = navigateBack,
        )

    }

}


@Serializable
data object LoginScreenRoute

@Serializable
data object RegisterScreenRoute