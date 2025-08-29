package org.akwapos.app.models.pos_product


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetails(
    @SerialName("allow_discount")
    val allowDiscount: Boolean?,
    @SerialName("base_price")
    val basePrice: Int?,
    @SerialName("category")
    val category: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("max_discount_percent")
    val maxDiscountPercent: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("tax_rate")
    val taxRate: Int?
)