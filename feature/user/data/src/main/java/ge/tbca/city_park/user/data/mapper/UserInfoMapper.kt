package ge.tbca.city_park.user.data.mapper

import ge.tbca.city_park.user.data.model.UserInfoDTO
import ge.tbca.city_park.user.domain.model.UserInfoDomain

fun UserInfoDTO.toDomain() :UserInfoDomain{
    return UserInfoDomain(
        id = id,
        userId = userId,
        email = email,
        parkingBalance = parkingBalance,
        fcmToken = fcmToken
    )
}