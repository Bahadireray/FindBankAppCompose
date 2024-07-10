package com.bahadireray.findbankapp.di

import com.bahadireray.findbankapp.data.local.BankDataRepository
import com.bahadireray.findbankapp.domain.repository.BankRepository
import com.bahadireray.findbankapp.domain.use_case.GetBankListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @ViewModelScoped
    @Provides
    fun provideGetBankListUseCase(
        bankRepository: BankRepository,
        bankDataRepository: BankDataRepository
    ): GetBankListUseCase {
        return GetBankListUseCase(
            bankRepository,
            bankDataRepository
        )
    }
}