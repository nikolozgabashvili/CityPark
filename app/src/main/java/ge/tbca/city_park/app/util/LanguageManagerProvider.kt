package ge.tbca.city_park.app.util

import android.content.Context

interface LanguageManagerProvider {
    fun provideLanguageManager(): LanguageManager
}

val Context.languageManager: LanguageManager
    get() = (applicationContext as LanguageManagerProvider).provideLanguageManager()
