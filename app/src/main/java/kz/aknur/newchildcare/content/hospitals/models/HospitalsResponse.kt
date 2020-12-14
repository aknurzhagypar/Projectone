package kz.aknur.newchildcare.content.hospitals.models

import com.google.gson.annotations.SerializedName
import kz.aknur.newchildcare.signUp.secondPage.models.CityModel

data class HospitalsResponse(
    @SerializedName("data")
    val data: List<HospitalModel>,
    @SerializedName("status")
    val status: String
)