package ge.tbca.city_park.settings.presentation

import ge.tbca.city_park.settings.domain.model.AppLanguage
import ge.tbca.city_park.settings.domain.model.AppThemeOption
import ge.tbca.city_park.settings.domain.usecase.GetSavedLanguageUseCase
import ge.tbca.city_park.settings.domain.usecase.GetSavedThemeUseCase
import ge.tbca.city_park.settings.presentation.settings.screen.SettingsEffect
import ge.tbca.city_park.settings.presentation.settings.screen.SettingsEvent
import ge.tbca.city_park.settings.presentation.settings.screen.SettingsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsViewModelTest {

    private val getSavedThemeUseCase: GetSavedThemeUseCase = mockk()
    private val getSavedLanguageUseCase: GetSavedLanguageUseCase = mockk()
    private lateinit var viewModel: SettingsViewModel

    // imitation of datastore flow
    private val selectedThemeChannel = Channel<AppThemeOption>(capacity = 1)
    private val selectedLanguageChannel = Channel<AppLanguage>(capacity = 1)

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        coEvery { getSavedThemeUseCase() } returns selectedThemeChannel.receiveAsFlow()
        coEvery { getSavedLanguageUseCase() } returns selectedLanguageChannel.receiveAsFlow()
        selectedThemeChannel.trySend(AppThemeOption.LIGHT)
        selectedLanguageChannel.trySend(AppLanguage.ENGLISH)
        viewModel = SettingsViewModel(getSavedThemeUseCase, getSavedLanguageUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init collects theme from getSavedThemeUseCase and updates state`() = runTest {


        // Assert
        assertEquals(AppThemeOption.LIGHT, viewModel.state.currentThemeMode)
    }

    @Test
    fun `init collects language from getSavedLanguageUseCase and updates state`() = runTest {

        // Assert
        assertEquals(AppLanguage.ENGLISH, viewModel.state.currentLanguage)
    }

    @Test
    fun `BackButtonClicked in onEvent sends NavigateBack side effect`() = runTest {
        var capturedEffect: SettingsEffect? =
            null

        val job = viewModel.effect
            .take(1)
            .onEach { capturedEffect = it }
            .launchIn(this)

        viewModel.onEvent(SettingsEvent.BackButtonClicked)

        // Wait for job to complete
        job.join()

        // Assert
        assertEquals(
            SettingsEffect.NavigateBack,
            capturedEffect
        )
    }

    @Test
    fun `NavigateToLanguageSettings in onEvent sends NavigateToLanguageSettings side effect`() =
        runTest {
            var capturedEffect: SettingsEffect? =
                null

            val job = viewModel.effect
                .take(1)
                .onEach { capturedEffect = it }
                .launchIn(this)

            viewModel.onEvent(SettingsEvent.NavigateToLanguageSettings)

            // Wait for job to complete
            job.join()

            // Assert
            assertEquals(
                SettingsEffect.NavigateToLanguageSettings,
                capturedEffect
            )
        }

    @Test
    fun `NavigateToThemeSettings in onEvent sends NavigateToThemeSettings side effect`() = runTest {
        var capturedEffect: SettingsEffect? =
            null

        val job = viewModel.effect
            .take(1)
            .onEach { capturedEffect = it }
            .launchIn(this)

        viewModel.onEvent(SettingsEvent.NavigateToThemeSettings)

        // Wait for job to complete
        job.join()

        // Assert
        assertEquals(SettingsEffect.NavigateToThemeSettings, capturedEffect)
    }
}