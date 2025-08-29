package org.akwapos.app.models.product


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatedByDetails(
    @SerialName("email")
    val email: String?,
    @SerialName("first_name")
    val firstName: String?,
    @SerialName("full_name")
    val fullName: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("last_name")
    val lastName: String?,
    @SerialName("profile_image")
    val profileImage: String?
)