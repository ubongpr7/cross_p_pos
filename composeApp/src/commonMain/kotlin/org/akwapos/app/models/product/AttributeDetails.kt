package org.akwapos.app.models.product


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttributeDetails(
    @SerialName("attribute_type")
    val attributeType: String?,
    @SerialName("display_order")
    val displayOrder: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("is_required")
    val isRequired: Boolean?,
    @SerialName("is_variant_attribute")
    val isVariantAttribute: Boolean?,
    @SerialName("name")
    val name: String?,
    @SerialName("unit")
    val unit: String?,
    @SerialName("values")
    val values: List<Value?>?,
    @SerialName("values_count")
    val valuesCount: Int?
)