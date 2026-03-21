package com.maplescouter.core.network

import okhttp3.Interceptor
import okhttp3.Response

class MapleApiKeyInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("x-nxopen-api-key", BuildConfig.API_KEY)
            .build()

        return chain.proceed(request)
    }
}