package ge.tbca.city_park.app

import ge.tbca.city_park.settings.domain.model.AppThemeOption

data class MainActivityState(
    val themeOption: AppThemeOption = AppThemeOption.SYSTEM,
    val isAuthorized: Boolean? = null,
){
    val isLoading:Boolean
        get() = isAuthorized == null
}
