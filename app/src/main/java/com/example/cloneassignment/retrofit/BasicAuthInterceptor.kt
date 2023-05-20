package com.example.cloneassignment.retrofit

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request();
        val authenticatedRequest = request.newBuilder()
            .header(
                "authorization",
                Credentials.basic(
                    "AIZ67DUSNZ8TGWJV4DZ7DI3T5Z2LN2W2",
                    "ASN9AVKHU6HF41KTU71G3KNXLG1ET7BC"
                )
            ).build();
        return chain.proceed(authenticatedRequest);
    }

}