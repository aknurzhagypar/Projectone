package kz.aknur.newchildcare.content.child.childlist

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import kz.aknur.newchildcare.common.preferences.UserSession
import kz.aknur.newchildcare.common.remote.ApiConstants
import kz.aknur.newchildcare.common.remote.Networking
import kz.aknur.newchildcare.content.child.childlist.models.ChildModel
import kz.aknur.newchildcare.content.home.categories.models.CategoriesModel
import kz.aknur.newchildcare.content.hospitals.models.HospitalModel
import kz.aknur.newchildcare.content.profile.models.ProfileInfoModel
import kz.aknur.newchildcare.signIn.models.AuthRequest
import kz.aknur.newchildcare.signUp.firstPage.models.UserDetailModel
import kz.aknur.newchildcare.signUp.secondPage.models.CityModel

class ChildListRepository(application: Application) {

    companion object {
        const val TAG = "ChildListRepository"
    }

    private val networkService =
        Networking.create(ApiConstants.APP_BASE_URL)
    private var sharedPreferences =
        application.getSharedPreferences("userSession", Context.MODE_PRIVATE)
    private var userSession: UserSession =
        UserSession(sharedPreferences)



    suspend fun getMyChildren(): List<ChildModel>?{
        val result = networkService.getMyChildList("Bearer "+ userSession.getAccessToken())
        return if (result.code() == 200){
            result.body()!!.data
        } else null
    }


}