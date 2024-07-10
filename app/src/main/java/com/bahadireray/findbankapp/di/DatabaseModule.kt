package com.bahadireray.findbankapp.di

import android.content.Context
import androidx.room.Room
import com.bahadireray.findbankapp.data.local.BankDatabase
import com.bahadireray.findbankapp.data.local.BankInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideBankDao(bankDatabase: BankDatabase): BankInfoDao {
        return bankDatabase.bankInfoDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): BankDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            BankDatabase::class.java,
            "bank_database"
        ).build()
    }
}