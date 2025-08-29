package org.akwapos.app.models.pos_product


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttributeDetail(
    @SerialName("attribute_link")
    val attributeLink: Int?,
    @SerialName("attribute_name")
    val attributeName: String?,
    @SerialName("attribute_type")
    val attributeType: String?,
    @SerialName("custom_modifier")
    val customModifier: String?,
    @SerialName("custom_value")
    val customValue: String?,
    @SerialName("display_value")
    val displayValue: String?,
    @SerialName("effective_modifier")
    val effectiveModifier: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("product")
    val product: String?,
    @SerialName("value")
    val value: Int?,
    @SerialName("value_details")
    val valueDetails: ValueDetails?,
    @SerialName("variant")
    val variant: String?
)