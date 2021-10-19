package com.pilltracker.movies.di.module.network

import android.content.Context
import com.pilltracker.movies.BuildConfig
import com.pilltracker.movies.network.NoInternetException
import com.pilltracker.movies.utils.NetworkUtils
import com.pilltracker.pilltracker_next.di.qualifiers.ApplicationContext
import com.pilltracker.pilltracker_next.di.qualifiers.ErrorsInterceptor
import com.pilltracker.pilltracker_next.di.qualifiers.HeaderInterceptor
import com.pilltracker.pilltracker_next.di.qualifiers.NetworkInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import javax.inject.Singleton

@Module
class InterceptorsModule {

    @Provides
    @Singleton
    @NetworkInterceptor
    fun provideConnectivityInterceptor(@ApplicationContext appContext: Context): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            if (!NetworkUtils.isInternetAvailable(appContext)) {
                throw NoInternetException("appContext.getString(R.string.generic_no_internet_title)")
            }
            val builder = chain.request().newBuilder()
            chain.proceed(builder.build())
        }
    }


    @Singleton
    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG){
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
//            requestBuilder.apply {
//                addHeader(NetworkConstants.CONTENT_TYPE, "application/json;charset=utf-8")
//                addHeader(NetworkConstants.ACCEPT, "application/json")
//            }

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

            val api: String = extractRequestType(request.body)
            printCall(request, response, null)

            val body: ResponseBody = bodyString!!.toResponseBody(contentType)

            try {
                response.code
            } catch (e: Exception) {
                printCall(request, response, e)
            }
            response.newBuilder().body(body).build()
        }
    }


    private fun extractRequestType(request: RequestBody?): String {

        var type = ""

        request?.let {
            val requestBodyString = try {
                val buffer = Buffer()
                it.writeTo(buffer)
                buffer.readUtf8()
            } catch (e: IOException) {
                e.printStackTrace()
                ""
            }

            try {
                val requestBody = JSONObject(requestBodyString)
                type = requestBody.getString("type")
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }

        return type

    }



    private fun printCall(request: Request, response: Response?, e: Exception?) {

        if(BuildConfig.DEBUG){
//            Timber.tag("RETROFIT_CALL").apply {
//                d("url:%s", request.url)
//                d("headers:%s", request.headers)
//                d("model:%s", bodyToString(request))
//                response?.let {
//                    d("response:%s", Gson().toJson(it.body))
//                    d("code:%s", it.code)
//                }
//                e?.let {
//                    d("error:%s", it.message)
//                }
            }
        }
    }

//    private fun bodyToString(request: Request): String? {
//        return try {
//            val copy = request.newBuilder().build()
//            val buffer = Buffer()
//            copy.body!!.writeTo(buffer)
//            buffer.readUtf8()
//        } catch (e: java.lang.Exception) {
//            "did not work"
//        }
//    }


//}