package com.horizon.bancamovil.data.di

import android.content.Context
import androidx.room.Room
import com.horizon.bancamovil.data.local.dao.BankingDao
import com.horizon.bancamovil.data.local.BankingDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//4 - Here you tell Hilt how to created the db instance and dao

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDb(
        @ApplicationContext appContext: Context
    ) : BankingDb {
        return Room.databaseBuilder(
            appContext,
            BankingDb::class.java,
            "banking_db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun providesDao(db: BankingDb) : BankingDao = db.bankingDao()

}