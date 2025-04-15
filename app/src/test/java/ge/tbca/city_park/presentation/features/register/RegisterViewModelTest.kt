package ge.tbca.city_park.presentation.features.register

import ge.tbca.city_park.domain.core.usecase.ValidateEmailUseCase
import ge.tbca.city_park.domain.core.usecase.ValidatePasswordUseCase
import ge.tbca.city_park.presentation.core.model.PasswordValidationState
import ge.tbca.city_park.presentation.features.register.screen.RegisterEffect
import ge.tbca.city_park.presentation.features.register.screen.RegisterEvent
import ge.tbca.city_park.presentation.features.register.screen.RegisterViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RegisterViewModelTest {

    private val validateEmailUseCase: ValidateEmailUseCase = mockk()
    private val validatePasswordUseCase: ValidatePasswordUseCase = mockk()

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        viewModel = RegisterViewModel(validateEmailUseCase, validatePasswordUseCase)
    }

    @Test
    fun `EmailChanged in onEvent updates email state`() = runTest {
        viewModel.onEvent(RegisterEvent.EmailChanged(VALID_EMAIL))

        // Assert
        assertEquals(VALID_EMAIL, viewModel.state.email)
    }

    @Test
    fun `PasswordChanged in onEvent updates password state`() = runTest {
        every { validatePasswordUseCase(VALID_PASSWORD) } returns PasswordValidationState(
            hasMinLength = true,
            hasUpperCase = true,
            hasLowerCase = true,
            hasDigit = true,
            hasSpecialChar = true
        )

        viewModel.onEvent(RegisterEvent.PasswordChanged(VALID_PASSWORD))

        // Assert
        assertEquals(VALID_PASSWORD, viewModel.state.password)
    }

    @Test
    fun `RepeatPasswordChanged in onEvent updates repeat password state`() = runTest {
        viewModel.onEvent(RegisterEvent.RepeatPasswordChanged(VALID_PASSWORD))

        // Assert
        assertEquals(VALID_PASSWORD, viewModel.state.repeatPassword)
    }

    @Test
    fun `PasswordVisibilityChanged in onEvent updates password visibility state`() = runTest {
        // Default state for password visibility is false
        viewModel.onEvent(RegisterEvent.PasswordVisibilityChanged)

        // Assert
        assertEquals(true, viewModel.state.isPasswordVisible)
    }

    @Test
    fun `RepeatPasswordVisibilityChanged in onEvent updates repeat password visibility state`() =
        runTest {
            // Default state for repeat password visibility is false
            viewModel.onEvent(RegisterEvent.RepeatPasswordVisibilityChanged)

            // Assert
            assertEquals(true, viewModel.state.isRepeatPasswordVisible)
        }

    @Test
    fun `Valid email, password and repeat password succeeds the registration`() = runTest {
        every { validateEmailUseCase(VALID_EMAIL) } returns true
        every { validatePasswordUseCase(VALID_PASSWORD) } returns PasswordValidationState(
            hasMinLength = true,
            hasUpperCase = true,
            hasLowerCase = true,
            hasDigit = true,
            hasSpecialChar = true
        )

        viewModel.onEvent(RegisterEvent.EmailChanged(VALID_EMAIL))
        viewModel.onEvent(RegisterEvent.PasswordChanged(VALID_PASSWORD))
        viewModel.onEvent(RegisterEvent.RepeatPasswordChanged(VALID_PASSWORD))

        viewModel.onEvent(RegisterEvent.RegisterButtonClicked)

        // Assert
        assertEquals(false, viewModel.state.showEmailError)
        assertEquals(false, viewModel.state.showPasswordError)
        assertEquals(false, viewModel.state.showRepeatPasswordError)
    }

    @Test
    fun `Invalid email fails the registration and updates email error state`() = runTest {
        every { validateEmailUseCase(INVALID_EMAIL) } returns false
        every { validatePasswordUseCase(VALID_PASSWORD) } returns PasswordValidationState(
            hasMinLength = true,
            hasUpperCase = true,
            hasLowerCase = true,
            hasDigit = true,
            hasSpecialChar = true
        )

        viewModel.onEvent(RegisterEvent.EmailChanged(INVALID_EMAIL))
        viewModel.onEvent(RegisterEvent.PasswordChanged(VALID_PASSWORD))
        viewModel.onEvent(RegisterEvent.RepeatPasswordChanged(VALID_PASSWORD))

        viewModel.onEvent(RegisterEvent.RegisterButtonClicked)

        // Assert
        assertEquals(true, viewModel.state.showEmailError)
    }

    @Test
    fun `Short password fails the registration and updates password error state`() = runTest {
        every { validateEmailUseCase(VALID_EMAIL) } returns true
        every { validatePasswordUseCase(SHORT_PASSWORD) } returns PasswordValidationState(
            hasMinLength = false,
            hasUpperCase = true,
            hasLowerCase = true,
            hasDigit = true,
            hasSpecialChar = true
        )

        viewModel.onEvent(RegisterEvent.EmailChanged(VALID_EMAIL))
        viewModel.onEvent(RegisterEvent.PasswordChanged(SHORT_PASSWORD))
        viewModel.onEvent(RegisterEvent.RepeatPasswordChanged(SHORT_PASSWORD))

        viewModel.onEvent(RegisterEvent.RegisterButtonClicked)

        // Assert
        assertEquals(true, viewModel.state.showPasswordError)
    }

    @Test
    fun `Password with no digits fails the registration and updates password error state`() =
        runTest {
            every { validateEmailUseCase(VALID_EMAIL) } returns true
            every { validatePasswordUseCase(NO_DIGIT_PASSWORD) } returns PasswordValidationState(
                hasMinLength = true,
                hasUpperCase = true,
                hasLowerCase = true,
                hasDigit = false,
                hasSpecialChar = true
            )

            viewModel.onEvent(RegisterEvent.EmailChanged(VALID_EMAIL))
            viewModel.onEvent(RegisterEvent.PasswordChanged(NO_DIGIT_PASSWORD))
            viewModel.onEvent(RegisterEvent.RepeatPasswordChanged(NO_DIGIT_PASSWORD))

            viewModel.onEvent(RegisterEvent.RegisterButtonClicked)

            // Assert
            assertEquals(true, viewModel.state.showPasswordError)
        }

    @Test
    fun `Password with no special chars fails the registration and updates password error state`() =
        runTest {
            every { validateEmailUseCase(VALID_EMAIL) } returns true
            every { validatePasswordUseCase(NO_SPECIAL_CHAR_PASSWORD) } returns PasswordValidationState(
                hasMinLength = true,
                hasUpperCase = true,
                hasLowerCase = true,
                hasDigit = true,
                hasSpecialChar = false
            )

            viewModel.onEvent(RegisterEvent.EmailChanged(VALID_EMAIL))
            viewModel.onEvent(RegisterEvent.PasswordChanged(NO_SPECIAL_CHAR_PASSWORD))
            viewModel.onEvent(RegisterEvent.RepeatPasswordChanged(NO_SPECIAL_CHAR_PASSWORD))

            viewModel.onEvent(RegisterEvent.RegisterButtonClicked)

            // Assert
            assertEquals(true, viewModel.state.showPasswordError)
        }

    @Test
    fun `Password with no uppercase fails the registration and updates password error state`() =
        runTest {
            every { validateEmailUseCase(VALID_EMAIL) } returns true
            every { validatePasswordUseCase(NO_UPPERCASE_PASSWORD) } returns PasswordValidationState(
                hasMinLength = true,
                hasUpperCase = false,
                hasLowerCase = true,
                hasDigit = true,
                hasSpecialChar = true
            )

            viewModel.onEvent(RegisterEvent.EmailChanged(VALID_EMAIL))
            viewModel.onEvent(RegisterEvent.PasswordChanged(NO_UPPERCASE_PASSWORD))
            viewModel.onEvent(RegisterEvent.RepeatPasswordChanged(NO_UPPERCASE_PASSWORD))

            viewModel.onEvent(RegisterEvent.RegisterButtonClicked)

            // Assert
            assertEquals(true, viewModel.state.showPasswordError)
        }

    @Test
    fun `Password with no lowercase fails the registration and updates password error state`() =
        runTest {
            every { validateEmailUseCase(VALID_EMAIL) } returns true
            every { validatePasswordUseCase(NO_LOWERCASE_PASSWORD) } returns PasswordValidationState(
                hasMinLength = true,
                hasUpperCase = true,
                hasLowerCase = false,
                hasDigit = true,
                hasSpecialChar = true
            )

            viewModel.onEvent(RegisterEvent.EmailChanged(VALID_EMAIL))
            viewModel.onEvent(RegisterEvent.PasswordChanged(NO_LOWERCASE_PASSWORD))
            viewModel.onEvent(RegisterEvent.RepeatPasswordChanged(NO_LOWERCASE_PASSWORD))

            viewModel.onEvent(RegisterEvent.RegisterButtonClicked)

            // Assert
            assertEquals(true, viewModel.state.showPasswordError)
        }

    @Test
    fun `GoogleButtonClicked in onEvent sends SignUpWithGoogle side effect`() = runTest {
        var capturedEffect: RegisterEffect? = null

        val job = viewModel.effect
            .take(1)
            .onEach { capturedEffect = it }
            .launchIn(this)

        viewModel.onEvent(RegisterEvent.GoogleButtonClicked)

        // Wait for job to complete
        job.join()

        // Assert
        assertEquals(RegisterEffect.SignUpWithGoogle, capturedEffect)
    }

    @Test
    fun `BackButtonClicked in onEvent sends NavigateBack side effect`() = runTest {
        var capturedEffect: RegisterEffect? = null

        val job = viewModel.effect
            .take(1)
            .onEach { capturedEffect = it }
            .launchIn(this)

        viewModel.onEvent(RegisterEvent.BackButtonClicked)

        // Wait for job to complete
        job.join()

        // Assert
        assertEquals(RegisterEffect.NavigateBack, capturedEffect)
    }

    companion object {
        private const val VALID_EMAIL = "test@gmail.com"
        private const val INVALID_EMAIL = "test@gmail"
        private const val VALID_PASSWORD = "Password123+@"
        private const val SHORT_PASSWORD = "Pwd1@+"
        private const val NO_DIGIT_PASSWORD = "Password+@"
        private const val NO_SPECIAL_CHAR_PASSWORD = "Password123"
        private const val NO_UPPERCASE_PASSWORD = "password123+@"
        private const val NO_LOWERCASE_PASSWORD = "PASSWORD123+@"
    }
}