package org.akwapos.app.models.product


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttributeLink(
    @SerialName("attribute")
    val attribute: Double?,
    @SerialName("attribute_details")
    val attributeDetails: AttributeDetails?,
    @SerialName("attribute_name")
    val attributeName: String?,
    @SerialName("attribute_options")
    val attributeOptions: List<AttributeOption?>?,
    @SerialName("attribute_type")
    val attributeType: String?,
    @SerialName("default_modifier")
    val defaultModifier: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("is_visible_in_pos")
    val isVisibleInPos: Boolean?,
    @SerialName("order")
    val order: Int?,
    @SerialName("product")
    val product: String?,
    @SerialName("required")
    val required: Boolean?
)