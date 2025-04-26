package ge.tbca.city_park.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AddUserRequest(
    val email:String
)
