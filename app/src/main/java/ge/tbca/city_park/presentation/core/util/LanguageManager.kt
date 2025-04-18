package ge.tbca.city_park.presentation.core.util

import android.content.Context
import android.content.res.Configuration
import android.os.LocaleList
import ge.tbca.city_park.domain.core.usecase.GetCurrentLanguageUseCase
import ge.tbca.city_park.domain.model.AppLanguage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageManager @Inject constructor(
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase,
) {
    private val currentLanguage: AppLanguage
        get() {
            val currentLang = runBlocking {
                getCurrentLanguageUseCase().first()
            }

            return AppLanguage.entries.firstOrNull { it==currentLang }
                ?: AppLanguage.GEORGIAN
        }

    fun updateResources(context: Context): Context {
        val newLocale = Locale(currentLanguage.localeString)
        Locale.setDefault(newLocale)
        val config = Configuration(context.resources.configuration)
        config.setLocales(LocaleList(newLocale))
        return context.createConfigurationContext(config)
    }
}