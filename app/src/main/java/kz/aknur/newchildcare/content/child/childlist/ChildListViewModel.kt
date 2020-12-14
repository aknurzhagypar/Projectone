package kz.aknur.newchildcare.content.child.childlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kz.aknur.newchildcare.signIn.models.AuthRequest
import kotlinx.coroutines.launch
import kz.aknur.newchildcare.content.child.childlist.models.ChildModel
import kz.aknur.newchildcare.content.home.categories.models.CategoriesModel
import kz.aknur.newchildcare.content.home.categories.models.LargeCategoriesModel
import kz.aknur.newchildcare.content.home.categories.models.SmallCategoriesModel
import kz.aknur.newchildcare.content.hospitals.models.HospitalModel
import java.lang.Exception

class ChildListViewModel(application: Application): AndroidViewModel(application) {

    companion object {
        const val TAG = "ChildListViewModel"
    }

    private var repository: ChildListRepository =
        ChildListRepository(
            application
        )

    val childlist: MutableLiveData<List<ChildModel>> = MutableLiveData()
    val isError: MutableLiveData<String> = MutableLiveData()


    fun getHospitals(){
        viewModelScope.launch {
            try {
                childlist.postValue(repository.getMyChildren())
            } catch (exception: Exception) {
                Log.e(TAG, exception.message.toString())
                isError.postValue(exception.toString())
            }
        }
    }
}