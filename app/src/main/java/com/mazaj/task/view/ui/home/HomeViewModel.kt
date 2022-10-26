package com.mazaj.task.view.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.mazaj.task.network.AppRepository
import com.mazaj.task.view.ui.home.entity.NeoBrowseModel
import com.mazaj.task.network.Resource
import kotlinx.coroutines.Dispatchers
import java.net.UnknownHostException

class HomeViewModel(private val appRepository: AppRepository) : ViewModel() {



    fun getBrowseData(page:Int , size : Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = appRepository.getBrowseData(page,size)))

        } catch (exception: Exception) {
            Log.d("ExceptionBrowseData ", exception.message.toString())
            if (exception is UnknownHostException) {
                emit(Resource.error(data = null, message = "Check Internet Connection"))
            } else emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred!"
                )
            )
        }
    }


}