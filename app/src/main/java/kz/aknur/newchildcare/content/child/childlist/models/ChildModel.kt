package kz.aknur.newchildcare.content.child.childlist.models

import com.google.gson.annotations.SerializedName
import kz.aknur.newchildcare.signUp.secondPage.models.CityModel

data class ChildModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("birthDate")
    val birthDate: String
)