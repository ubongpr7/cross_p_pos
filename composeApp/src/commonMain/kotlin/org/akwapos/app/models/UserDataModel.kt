package org.akwapos.app.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDataModel(
    @SerialName("access")
    val access: String?,
    @SerialName("agent_name")
    val agentName: String?,
    @SerialName("api_key")
    val apiKey: String?,
    @SerialName("currency")
    val currency: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("first_name")
    val firstName: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("is_main")
    val isMain: Boolean?,
    @SerialName("is_verified")
    val isVerified: Boolean?,
    @SerialName("is_worker")
    val isWorker: Boolean?,
    @SerialName("model_name")
    val modelName: String?,
    @SerialName("profile")
    val profile: Int?,
    @SerialName("provider")
    val provider: String?,
    @SerialName("refresh")
    val refresh: String?,
    @SerialName("tavily_api_key")
    val tavilyApiKey: String?,
    @SerialName("username")
    val username: String?
)