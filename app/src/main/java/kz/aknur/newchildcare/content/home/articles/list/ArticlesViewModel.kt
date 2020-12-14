package kz.aknur.newchildcare.content.home.articles.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.aknur.newchildcare.content.home.articles.ArticleModel
import java.lang.Exception

class ArticlesViewModel(application: Application): AndroidViewModel(application) {

    companion object {
        const val TAG = "ArticlesViewModel"
    }

    private var repository: ArticlesRepository =
        ArticlesRepository(
            application
        )

    val articles: MutableLiveData<List<ArticleModel>> = MutableLiveData()
    val isError: MutableLiveData<String> = MutableLiveData()

    fun getArticlesByCategory(categoryId: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getArticles(categoryId)
                articles.postValue(result)
            } catch (exception: Exception) {
                Log.e(TAG, exception.message.toString())
                isError.postValue(exception.toString())
            }
        }
    }
}