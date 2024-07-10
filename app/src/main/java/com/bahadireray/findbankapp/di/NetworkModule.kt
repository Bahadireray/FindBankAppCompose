package com.bahadireray.findbankapp.di

import android.content.Context
import com.bahadireray.findbankapp.common.Constants
import com.bahadireray.findbankapp.data.network.DynamicBaseUrlInterceptor
import com.bahadireray.findbankapp.data.remote.BankService
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun getGson(): Gson {
        return GsonBuilder().serializeNulls().setLenient().create()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideDynamicBaseUrlInterceptor(): DynamicBaseUrlInterceptor = DynamicBaseUrlInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        dynamicBaseUrlInterceptor: DynamicBaseUrlInterceptor
    ): OkHttpClient {
        val chuckerInterceptor = ChuckerInterceptor(context)
        return OkHttpClient.Builder()
            .readTimeout(Constants.API_SERVICE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.API_SERVICE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(dynamicBaseUrlInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): BankService {
        return retrofit.create(BankService::class.java)
    }
}