package org.akwapos.app.models.product


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValueDetails(
    @SerialName("attribute")
    val attribute: Double?,
    @SerialName("attribute_name")
    val attributeName: String?,
    @SerialName("attribute_type")
    val attributeType: String?,
    @SerialName("color_code")
    val colorCode: String?,
    @SerialName("display_value")
    val displayValue: String?,
    @SerialName("effective_display_value")
    val effectiveDisplayValue: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("is_active")
    val isActive: Boolean?,
    @SerialName("sort_order")
    val sortOrder: Int?,
    @SerialName("value")
    val value: String?
)