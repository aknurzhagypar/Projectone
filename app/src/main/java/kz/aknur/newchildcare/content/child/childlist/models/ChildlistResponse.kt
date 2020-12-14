package kz.aknur.newchildcare.content.child.childlist.models

import com.google.gson.annotations.SerializedName
import kz.aknur.newchildcare.signUp.secondPage.models.CityModel

data class ChildlistResponse(
    @SerializedName("data")
    val data: List<ChildModel>,
    @SerializedName("status")
    val status: String
)