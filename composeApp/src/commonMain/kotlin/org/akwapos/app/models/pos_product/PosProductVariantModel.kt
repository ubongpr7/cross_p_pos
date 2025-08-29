package org.akwapos.app.models.pos_product


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PosProductVariantModel(
    @SerialName("active")
    val active: Boolean?,
    @SerialName("attachments")
    val attachments: List<Attachment?>?,
    @SerialName("attribute_details")
    val attributeDetails: List<AttributeDetail?>?,
    @SerialName("cost_override")
    val costOverride: String?,
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("created_by")
    val createdBy: String?,
    @SerialName("created_by_details")
    val createdByDetails: CreatedByDetails?,
    @SerialName("dimensions_override")
    val dimensionsOverride: String?,
    @SerialName("effective_properties")
    val effectiveProperties: EffectiveProperties?,
    @SerialName("id")
    val id: String?,
    @SerialName("is_featured")
    val isFeatured: Boolean?,
    @SerialName("low_stock_threshold_override")
    val lowStockThresholdOverride: String?,
    @SerialName("main_image")
    val mainImage: String?,
    @SerialName("pos_display_name")
    val posDisplayName: String?,
    @SerialName("pos_name")
    val posName: String?,
    @SerialName("pos_price")
    val posPrice: Int?,
    @SerialName("pos_visible")
    val posVisible: Boolean?,
    @SerialName("price_override")
    val priceOverride: Double?,
    @SerialName("product")
    val product: String?,
    @SerialName("product_details")
    val productDetails: ProductDetails?,
    @SerialName("selling_price")
    val sellingPrice: Int?,
    @SerialName("stock_details")
    val stockDetails: String?,
    @SerialName("track_stock_override")
    val trackStockOverride: String?,
    @SerialName("updated_at")
    val updatedAt: String?,
    @SerialName("variant_barcode")
    val variantBarcode: String?,
    @SerialName("variant_number")
    val variantNumber: Int?,
    @SerialName("variant_sku")
    val variantSku: String?,
    @SerialName("weight_override")
    val weightOverride: Double?
)