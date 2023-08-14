package com.example.interviewapplication.hilt

import com.example.interviewapplication.remote.TravelInterface
import com.example.interviewapplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Singleton
    @Provides
    fun provideRetrofitInterface(): TravelInterface{
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val request: Request =
                chain.request().newBuilder().addHeader("accept", "application/json").build()
            chain.proceed(request)
        }
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TravelInterface::class.java)
    }
}