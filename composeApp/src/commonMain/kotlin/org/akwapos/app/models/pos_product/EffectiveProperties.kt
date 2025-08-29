package org.akwapos.app.models.pos_product


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EffectiveProperties(
    @SerialName("barcode")
    val barcode: String?,
    @SerialName("cost_price")
    val costPrice: Double?,
    @SerialName("dimensions")
    val dimensions: String?,
    @SerialName("sku")
    val sku: String?,
    @SerialName("track_stock")
    val trackStock: Boolean?,
    @SerialName("weight")
    val weight: Double?
)