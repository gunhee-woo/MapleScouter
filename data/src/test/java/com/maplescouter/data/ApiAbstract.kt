package com.maplescouter.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.nio.charset.StandardCharsets
import kotlin.collections.iterator

@RunWith(RobolectricTestRunner::class)
abstract class ApiAbstract<T> {

    @Rule
    @JvmField
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    lateinit var mockWebServer: MockWebServer

    private val moshi = Moshi.Builder().build()
    private val jsonAdapter = moshi.adapter(Any::class.java).indent("  ")

    @Before
    fun mockServer() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }

    fun enqueueResponse(fileName: String) {
        enqueueResponse(fileName, emptyMap())
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String>) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
    }

    private fun prettyPrintJson(message: String): String {
        return if (message.startsWith("{") || message.startsWith("[")) {
            try {
                val json = jsonAdapter.fromJson(message)
                jsonAdapter.toJson(json)
            } catch (e: Exception) {
                message
            }
        } else {
            message
        }
    }

    fun createMockWebService(clazz: Class<T>): T {
        val logger = HttpLoggingInterceptor { message ->
            println("Mock HTTP Log : ${prettyPrintJson(message)}")
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(clazz)
    }

    fun createService(clazz: Class<T>): T {
        val logger = HttpLoggingInterceptor { message ->
            println("HTTP Log : ${prettyPrintJson(message)}")
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val mapleApiKeyInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()

            val newRequest = originalRequest.newBuilder()
                .header("x-nxopen-api-key", BuildConfig.API_KEY)
                .build()

            chain.proceed(newRequest)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(mapleApiKeyInterceptor)
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://open.api.nexon.com/maplestory/v1/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(clazz)
    }
}