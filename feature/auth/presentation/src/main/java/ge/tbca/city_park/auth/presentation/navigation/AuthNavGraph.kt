package ge.tbca.city_park.auth.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tbca.city_park.auth.presentation.screen.change_password.ChangePasswordScreenRoot
import ge.tbca.city_park.auth.presentation.screen.login.LoginScreenRoot
import ge.tbca.city_park.auth.presentation.screen.recover_password.RecoverPasswordScreenRoot
import ge.tbca.city_park.auth.presentation.screen.register.RegisterScreenRoot
import kotlinx.serialization.Serializable


fun NavGraphBuilder.authNavGraph(
    onShowSnackBar:  (String) -> Unit,
    navigateToRegister: () -> Unit,
    navigateToRecovery: () -> Unit,
    navigateBack: () -> Unit
) {
    composable<LoginScreenRoute> {
        LoginScreenRoot(
            onShowSnackBar = onShowSnackBar,
            navigateToRegister = navigateToRegister,
            navigateToRecovery = navigateToRecovery
        )

    }

    composable<RegisterScreenRoute> {
        RegisterScreenRoot(
            onShowSnackBar = onShowSnackBar,
            navigateBack = navigateBack
        )

    }

    composable<RecoverPasswordScreenRoute> {
        RecoverPasswordScreenRoot(
            navigateBack = navigateBack,
            onShowSnackBar = onShowSnackBar
        )

    }


    composable<ChangePasswordScreenRoute> {
        ChangePasswordScreenRoot(
            onShowSnackBar = onShowSnackBar,
            navigateBack = navigateBack
        )

    }

}


@Serializable
data object LoginScreenRoute

@Serializable
data object RegisterScreenRoute

@Serializable
data object RecoverPasswordScreenRoute

@Serializable
data object ChangePasswordScreenRoute