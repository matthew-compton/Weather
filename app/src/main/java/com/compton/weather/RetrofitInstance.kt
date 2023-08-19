package com.compton.weather

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private const val QUERY_PARAM_API_KEY = "appid"

    private val retrofit: Retrofit by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val queryInterceptor = Interceptor { chain: Interceptor.Chain ->
            var request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter(QUERY_PARAM_API_KEY, BuildConfig.API_KEY)
                .build()
            request = request.newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            addInterceptor(queryInterceptor)
        }.build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val weatherService: WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }

}