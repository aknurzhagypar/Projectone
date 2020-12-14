package kz.aknur.newchildcare.content.home.articles

import com.google.gson.annotations.SerializedName

data class ArticleModel (
    @SerializedName("id")
    val id: Int,
    @SerializedName("topic")
    val topic: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("icon")
    val icon: ByteArray?
)
