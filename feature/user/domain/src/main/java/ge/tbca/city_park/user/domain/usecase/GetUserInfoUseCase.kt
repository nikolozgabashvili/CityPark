package ge.tbca.city_park.user.domain.usecase

import ge.tbca.city_park.user.domain.repository.UserInfoRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
) {
    operator fun invoke() = userInfoRepository.userData
}