package org.akwapos.app.models.product


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceRange(
    @SerialName("max")
    val max: Double?,
    @SerialName("min")
    val min: Double?
)