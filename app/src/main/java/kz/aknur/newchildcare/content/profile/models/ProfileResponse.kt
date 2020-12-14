package kz.aknur.newchildcare.content.profile.models

import com.google.gson.annotations.SerializedName
import kz.aknur.newchildcare.signUp.secondPage.models.CityModel

data class ProfileResponse(
    @SerializedName("data")
    val data: ProfileInfoModel,
    @SerializedName("status")
    val status: String
)