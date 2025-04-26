package ge.tbca.city_park.user.domain.usecase

import ge.tbca.city_park.user.domain.repository.UserInfoRepository
import javax.inject.Inject

class FetchUserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
) {
    suspend operator fun invoke() = userInfoRepository.fetchUserData()
}