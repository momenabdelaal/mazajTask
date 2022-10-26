package com.mazaj.task.network

import android.util.Log
import com.mazaj.task.view.utilties.Constants
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ConnectionRetrofit {


    companion object {



        // create a network cache interceptor, setting the max age to 1 minute
        private val networkCacheInterceptor = Interceptor { chain ->
            val response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.MINUTES)
                .build()

            response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }

        private val retrofitted by lazy {
            val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.e("response", message)
                }
            })


            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                // added certificates to compare at runtime with server's ??
                .certificatePinner(CertificatePinner.DEFAULT)
                .hostnameVerifier(UnsafeSSLOkHttpClient)

                // enough time to be able to edit the response in burp suite
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                //cache interceptor
                .addNetworkInterceptor(networkCacheInterceptor)
               // .cache(cache)

                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        }


        val apis by lazy {
            retrofitted.create(Api::class.java)
        }

    }


}