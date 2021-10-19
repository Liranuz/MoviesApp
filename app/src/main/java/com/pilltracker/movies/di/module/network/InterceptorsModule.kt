package com.pilltracker.movies.di.module.network

import android.content.Context
import com.pilltracker.movies.BuildConfig
import com.pilltracker.pilltracker_next.di.qualifiers.ApplicationContext
import com.pilltracker.pilltracker_next.di.qualifiers.ErrorsInterceptor
import com.pilltracker.pilltracker_next.di.qualifiers.HeaderInterceptor
import com.pilltracker.pilltracker_next.di.qualifiers.NetworkInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class InterceptorsModule {

    @Provides
    @Singleton
    @NetworkInterceptor
    fun provideConnectivityInterceptor(@ApplicationContext appContext: Context): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val builder = chain.request().newBuilder()
            chain.proceed(builder.build())
        }
    }


    @Singleton
    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

        }
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    @HeaderInterceptor
    internal fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            chain.proceed(requestBuilder.build())
        }
    }


    @Provides
    @Singleton
    @ErrorsInterceptor
    fun provideErrorsInterceptor(): Interceptor {

        return Interceptor { chain: Interceptor.Chain ->
            val request: Request = chain.request()
            val response: Response = chain.proceed(request)
            val contentType: MediaType? = response.body?.contentType()
            val bodyString = response.body?.string()
            val body: ResponseBody = bodyString!!.toResponseBody(contentType)

            try {
                response.code
            } catch (e: Exception) {
            }
            response.newBuilder().body(body).build()
        }
    }
}