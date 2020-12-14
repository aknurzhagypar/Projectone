package kz.aknur.newchildcare.content.profile.models

import com.google.gson.annotations.SerializedName
import kz.aknur.newchildcare.signUp.secondPage.models.CityModel

data class ProfileInfoModel(
    @SerializedName("text")
    val text: String
)