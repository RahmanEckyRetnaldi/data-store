package com.naky.datastoreapp.viewmodel

import android.content.Context
import androidx.datastore.dataStore
import androidx.lifecycle.*
import com.naky.datastoreapp.repository.DataStorePreferancesRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DataStoreViewModel():ViewModel() {
    private val _userName = MutableLiveData("")
    val userName : LiveData<String> = _userName

    suspend fun saveUserName(context : Context,userName : String){
        val dataStorePreferancesRepository: DataStorePreferancesRepository = DataStorePreferancesRepository.getInstance(context)!!
        dataStorePreferancesRepository.setUsername(context,userName)
    }
    fun getUserName(context : Context){
      val dataStorePreferancesRepository: DataStorePreferancesRepository = DataStorePreferancesRepository.getInstance(context)!!
        viewModelScope.launch {
            dataStorePreferancesRepository.getUserName.collect{
                _userName.value = it
            }
        }
    }


}




