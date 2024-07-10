package com.bahadireray.findbankapp.di

import com.bahadireray.findbankapp.data.remote.BankService
import com.bahadireray.findbankapp.data.repository.BankRepositoryImpl
import com.bahadireray.findbankapp.domain.repository.BankRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BankModule {

    @Provides
    @Singleton
    fun provideBankRepository(
        service: BankService,
    ): BankRepository = BankRepositoryImpl(service)
}
