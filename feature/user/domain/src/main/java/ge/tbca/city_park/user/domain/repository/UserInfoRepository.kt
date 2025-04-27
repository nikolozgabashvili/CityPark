package ge.tbca.city_park.user.domain.repository

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.user.domain.model.UserInfoDomain
import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {

    suspend fun fetchUserData(): Flow<Resource<UserInfoDomain, ApiError>>
}