package com.example.registro

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsManager (private val context: Context){

    val d_theme : Flow<Boolean>
        get() = context.settingsDataStore.data.map { preferences ->
            preferences[DARK_MODE]?:false
        }
    val sizeF:Flow<Int>
    get() = context.settingsDataStore.data.map { preferences ->
        preferences[SIZE_FUENTE] ?:20
    }

    suspend fun setDarkTheme(dark: Boolean){
        context.settingsDataStore.edit {  preferences ->
            preferences[DARK_MODE]= dark
        }
    }
    suspend fun  setSizeF(size : Int){
        context.settingsDataStore.edit {  preferences ->
            preferences[SIZE_FUENTE]= size
        }
    }


    companion object {
        private const val DATASTORE_NAME ="settings_preferences"

        private val DARK_MODE = booleanPreferencesKey("dark_mode")
        private val SIZE_FUENTE = intPreferencesKey("size_fuente")

        private  val  Context.settingsDataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}