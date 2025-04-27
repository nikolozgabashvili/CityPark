package ge.tbca.city_park.user.data.repository

import ge.tbca.city_park.core.data.extension.mapResource
import ge.tbca.city_park.core.data.helper.ApiHelper
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.user.data.mapper.toDomain
import ge.tbca.city_park.user.data.service.UserApiService
import ge.tbca.city_park.user.domain.model.UserInfoDomain
import ge.tbca.city_park.user.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val userApiService: UserApiService
) : UserInfoRepository {


    override suspend fun fetchUserData(): Flow<Resource<UserInfoDomain,ApiError>> {
        return apiHelper.safeCall {
            userApiService.getUserInfo()
        }.mapResource { it.toDomain() }

    }
}