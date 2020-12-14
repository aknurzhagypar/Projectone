package kz.aknur.newchildcare.content.child.add.models

import com.google.gson.annotations.SerializedName
import kz.aknur.newchildcare.signUp.secondPage.models.CityModel

data class ChildAddRequest(
    @SerializedName("birthDate")
    val birthDate: String,
    @SerializedName("disease")
    val disease: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("parentId")
    val parentId: Int,
    @SerializedName("weight")
    val weight: Int
)