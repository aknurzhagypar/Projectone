package kz.aknur.newchildcare.content.home.categories.models

import com.google.gson.annotations.SerializedName
import kz.aknur.newchildcare.signIn.models.DataModel

data class MainCategoriesResponse (
    @SerializedName("data")
    val data: CategoriesModel,
    @SerializedName("status")
    val status: String
)