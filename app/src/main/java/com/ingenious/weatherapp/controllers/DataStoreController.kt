package com.ingenious.weatherapp.controllers

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "appPreferences")

class DataStoreController(private val context: Context) {

    private val KEY_IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

    private val KEY_LOCATION_KEY = stringPreferencesKey("location_key")

    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[KEY_IS_LOGGED_IN] ?: false
    }

    suspend fun setLoggedIn(isLoggedIn: Boolean){
         context.dataStore.edit { preferences ->
            preferences[KEY_IS_LOGGED_IN] = isLoggedIn
        }
    }

    val locationKey: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[KEY_LOCATION_KEY] ?: ""
    }

    suspend fun setLocationKey(locationKey : String){
        context.dataStore.edit { preferences ->
            preferences[KEY_LOCATION_KEY] = locationKey
        }
    }



}