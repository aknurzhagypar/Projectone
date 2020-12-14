package kz.aknur.newchildcare.content.home.articles.list

import android.app.Application
import android.content.Context
import kz.aknur.newchildcare.common.preferences.UserSession
import kz.aknur.newchildcare.common.remote.ApiConstants
import kz.aknur.newchildcare.common.remote.Networking
import kz.aknur.newchildcare.content.home.articles.ArticleModel

class ArticlesRepository(application: Application) {

    companion object {
        const val TAG = "ArticlesRepository"
    }

    private val networkService =
        Networking.create(ApiConstants.APP_BASE_URL)
    private var sharedPreferences =
        application.getSharedPreferences("userSession", Context.MODE_PRIVATE)
    private var userSession: UserSession =
        UserSession(sharedPreferences)

    suspend fun getArticles(categoryId: Int) : List<ArticleModel>?{
        val result = networkService.getArticles(categoryId.toString(), "Bearer ${userSession.getAccessToken()}")
        return if (result.body()?.status == "success"){
            result.body()!!.data
        } else {
            null
        }
    }


}