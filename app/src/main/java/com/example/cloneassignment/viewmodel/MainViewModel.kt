package com.example.cloneassignment.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cloneassignment.repository.MainRepository
import com.example.cloneassignment.retrofit.UploadResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var mainRepository: MainRepository
) : ViewModel() {
    var email = "abc@gmail.com"
    var name = "abc"
    var livedata: MutableLiveData<UploadResponse?>? = null
    fun uploadDocument(
        fileName: String,
        fileData: String
    ): MutableLiveData<UploadResponse?>? {
        viewModelScope.launch(Dispatchers.IO) {
            var response =
                mainRepository.uploadPdfDocument(email, name, "assignment", fileName, fileData)
            livedata?.postValue(response)
        }
        Log.d(TAG, "uploadDocument: livedata,  $livedata")
        return livedata

    }

}