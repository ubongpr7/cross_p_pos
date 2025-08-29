package org.akwapos.app.models.pos_product


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PosProductModel(
    @SerialName("count")
    val count: Int?,
    @SerialName("page")
    val page: Int?,
    @SerialName("page_size")
    val pageSize: Int?,
    @SerialName("results")
    val results: List<ProductResult> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int?
)