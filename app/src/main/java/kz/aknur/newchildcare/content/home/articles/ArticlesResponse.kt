package kz.aknur.newchildcare.content.home.articles

import com.google.gson.annotations.SerializedName
import kz.aknur.newchildcare.content.home.categories.models.CategoriesModel

data class ArticlesResponse (
    @SerializedName("data")
    val data: List<ArticleModel>,
    @SerializedName("status")
    val status: String
)