package com.horizon.bancamovil.data.di

import android.content.Context
import android.content.SharedPreferences
import com.horizon.bancamovil.constans.PREFS_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {

    @Provides
    @Singleton
    fun providesSharedPref(@ApplicationContext context: Context) : SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providesSharedPrefEdit(
        sharedPreferences: SharedPreferences
    ) : SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

}