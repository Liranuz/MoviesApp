package com.pilltracker.movies.di.module.network

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pilltracker.movies.data.network.ApiEndPoint
import com.pilltracker.movies.data.network.NetworkConstants
import com.pilltracker.movies.data.network.NetworkController
import com.pilltracker.movies.data.network.NetworkControllerImpl
import com.pilltracker.pilltracker_next.di.qualifiers.ApplicationContext
import com.pilltracker.pilltracker_next.di.qualifiers.ErrorsInterceptor
import com.pilltracker.pilltracker_next.di.qualifiers.HeaderInterceptor
import com.pilltracker.pilltracker_next.di.qualifiers.NetworkInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [InterceptorsModule::class])
class NetworkModule {

    companion object {
        const val TIMEOUT = 60L
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        gsonBuilder.setLenient()
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @NetworkInterceptor networkInterceptor : Interceptor,
        @HeaderInterceptor headerInterceptor : Interceptor,
        @ErrorsInterceptor errorsInterceptor : Interceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(networkInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(errorsInterceptor)
            .addInterceptor(headerInterceptor)
            .build()

    }



    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(NetworkConstants.baseUrl)
            .client(okHttpClient)
            .build()

    }


    @Singleton
    @Provides
    fun provideMoviesApi(retrofit: Retrofit): ApiEndPoint {
        return retrofit.create(ApiEndPoint::class.java)
    }


    @Singleton
    @Provides
    fun provideNetworkController(
        @ApplicationContext context: Context,
        apiEndPoint: ApiEndPoint
    ): NetworkController {
        return NetworkControllerImpl(context, apiEndPoint)
    }




}