package org.akwapos.app.models.pos_product


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResult(
    @SerialName("allow_discount")
    val allowDiscount: Boolean?,
    @SerialName("barcode")
    val barcode: String?,
    @SerialName("base_price")
    val basePrice: String?,
    @SerialName("can_sell")
    val canSell: Boolean?,
    @SerialName("category_info")
    val categoryInfo: String?,
    @SerialName("display_name")
    val displayName: String?,
    @SerialName("effective_barcode")
    val effectiveBarcode: String?,
    @SerialName("featured_variant")
    val featuredVariant: PosProductVariantModel?,
    @SerialName("id")
    val id: String?,
    @SerialName("is_active")
    val isActive: Boolean?,
    @SerialName("main_image")
    val mainImage: String?,
    @SerialName("max_discount_percent")
    val maxDiscountPercent: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("pos_category")
    val posCategory: String?,
    @SerialName("pos_price")
    val posPrice: Double?,
    @SerialName("profit_margin")
    val profitMargin: Double?,
    @SerialName("quick_sale")
    val quickSale: Boolean?,
    @SerialName("sku")
    val sku: String?,
    @SerialName("stock_quantity")
    val stockQuantity: Int?,
    @SerialName("tax_inclusive")
    val taxInclusive: Boolean?,
    @SerialName("tax_rate")
    val taxRate: String?,
    @SerialName("unit")
    val unit: String?
)