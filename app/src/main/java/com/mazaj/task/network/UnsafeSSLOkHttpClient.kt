package com.mazaj.task.network


import android.util.Log
import okhttp3.OkHttpClient
import java.lang.Exception
import java.lang.RuntimeException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*
import kotlin.Throws
import javax.net.ssl.HostnameVerifier as HostnameVerifier1

class UnsafeSSLOkHttpClient  {

    companion object : HostnameVerifier1 {
        fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
            try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                        return arrayOf()
                    }
                })

                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory

                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                // builder.hostnameVerifier { _, _ -> true }
                builder.hostnameVerifier ( hostnameVerifier = HostnameVerifier1 { _, _ -> true })

                return builder
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        override fun verify(p0: String?, p1: SSLSession?): Boolean {
            Log.e("tag", "verify$p0$p1")
            return true
        }
    }
}