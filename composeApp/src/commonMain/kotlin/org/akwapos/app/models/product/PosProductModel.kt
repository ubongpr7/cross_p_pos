package org.akwapos.app.models.product


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PosProductModel(
    @SerialName("allow_backorder")
    val allowBackorder: Boolean?,
    @SerialName("allow_discount")
    val allowDiscount: Boolean?,
    @SerialName("attribute_links")
    val attributeLinks: List<AttributeLink?>?,
    @SerialName("average_cost")
    val averageCost: String?,
    @SerialName("barcode")
    val barcode: String?,
    @SerialName("base_price")
    val basePrice: String?,
    @SerialName("category")
    val category: String?,
    @SerialName("cost_price")
    val costPrice: String?,
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("created_by")
    val createdBy: String?,
    @SerialName("created_by_details")
    val createdByDetails: CreatedByDetails?,
    @SerialName("description")
    val description: String?,
    @SerialName("dimensions")
    val dimensions: String?,
    @SerialName("discontinue_date")
    val discontinueDate: String?,
    @SerialName("display_image")
    val displayImage: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("inventory")
    val inventory: String?,
    @SerialName("is_active")
    val isActive: Boolean?,
    @SerialName("is_featured")
    val isFeatured: Boolean?,
    @SerialName("is_template")
    val isTemplate: Boolean?,
    @SerialName("launch_date")
    val launchDate: String?,
    @SerialName("low_stock_threshold")
    val lowStockThreshold: Int?,
    @SerialName("low_stock_variants")
    val lowStockVariants: Int?,
    @SerialName("max_discount_percent")
    val maxDiscountPercent: String?,
    @SerialName("meta_description")
    val metaDescription: String?,
    @SerialName("meta_title")
    val metaTitle: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("out_of_stock_variants")
    val outOfStockVariants: Int?,
    @SerialName("pos_category")
    val posCategory: String?,
    @SerialName("pos_ready")
    val posReady: Boolean?,
    @SerialName("price_range")
    val priceRange: PriceRange?,
    @SerialName("pricing_strategy_details")
    val pricingStrategyDetails: String?,
    @SerialName("profit_margin")
    val profitMargin: Double?,
    @SerialName("quick_sale")
    val quickSale: Boolean?,
    @SerialName("quick_sale_variants")
    val quickSaleVariants: List<String?>?,
    @SerialName("short_description")
    val shortDescription: String?,
    @SerialName("sku")
    val useBarcode: String?,
    @SerialName("tax_inclusive")
    val taxInclusive: Boolean?,
    @SerialName("tax_rate")
    val taxRate: String?,
    @SerialName("total_stock")
    val totalStock: Int?,
    @SerialName("track_stock")
    val trackStock: Boolean?,
    @SerialName("unit")
    val unit: String?,
    @SerialName("updated_at")
    val updatedAt: String?,
    @SerialName("variant_count")
    val variantCount: Int?,
    @SerialName("weight")
    val weight: String?
)