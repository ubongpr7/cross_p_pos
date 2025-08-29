package org.akwapos.app.models.pos_product


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attachment(
    @SerialName("content_type")
    val contentType: Int?,
    @SerialName("description")
    val description: String?,
    @SerialName("file")
    val `file`: String?,
    @SerialName("file_size")
    val fileSize: Int?,
    @SerialName("file_size_formatted")
    val fileSizeFormatted: String?,
    @SerialName("file_type")
    val fileType: String?,
    @SerialName("file_url")
    val fileUrl: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("is_primary")
    val isPrimary: Boolean?,
    @SerialName("mime_type")
    val mimeType: String?,
    @SerialName("object_id")
    val objectId: String?,
    @SerialName("purpose")
    val purpose: String?,
    @SerialName("uploaded_at")
    val uploadedAt: String?,
    @SerialName("uploaded_by")
    val uploadedBy: String?,
    @SerialName("uploaded_by_details")
    val uploadedByDetails: UploadedByDetails?
)