package com.naky.datastoreapp.repository


import android.content.Context
import androidx.datastore.core.DataStore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



class DataStorePreferancesRepository (context: Context){
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "SampleData")
    private val usernameDefault = ""


    companion object{
        val PREF_USERNAME = stringPreferencesKey("user_name")
        private var INSTANCE  : DataStorePreferancesRepository? = null

        fun getInstance(context: Context) : DataStorePreferancesRepository? {
            return INSTANCE ?: synchronized(this){
                val instance = DataStorePreferancesRepository(context)
                INSTANCE = instance
                instance
            }
        }

    }
    suspend fun setUsername(context: Context,username : String){
        context.dataStore.edit {
            preferences ->
            preferences[PREF_USERNAME] = username
        }
    }

   val getUserName : Flow<String> = context.dataStore.data.map {
       preferences ->
       preferences[PREF_USERNAME] ?: usernameDefault
   }
}