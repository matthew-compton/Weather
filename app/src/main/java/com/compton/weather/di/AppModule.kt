package com.compton.weather.di

import com.compton.weather.BuildConfig
import com.compton.weather.data.remote.WeatherService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val QUERY_PARAM_API_KEY = "appid"
private const val QUERY_PARAM_UNITS = "units"
private const val QUERY_PARAM_UNITS_IMPERIAL = "imperial"

val appModule = module {
    single { provideLoggingInterceptor() }
    single { provideQueryInterceptor() }
    single { provideOkHttpClient(get(), get()) }
    single { provideRetrofit(get()) }
    single { provideWeatherService(get()) }
}

private fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
    setLevel(HttpLoggingInterceptor.Level.BODY)
}

private fun provideQueryInterceptor() = Interceptor { chain: Interceptor.Chain ->
    var request = chain.request()
    val url = request.url.newBuilder()
        .addQueryParameter(QUERY_PARAM_API_KEY, BuildConfig.API_KEY)
        .addQueryParameter(QUERY_PARAM_UNITS, QUERY_PARAM_UNITS_IMPERIAL)
        .build()
    request = request.newBuilder()
        .url(url)
        .build()
    chain.proceed(request)
}

private fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    queryInterceptor: Interceptor
) = OkHttpClient.Builder().apply {
    addInterceptor(loggingInterceptor)
    addInterceptor(queryInterceptor)
}.build()

private fun provideRetrofit(
    client: OkHttpClient
): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

private fun provideWeatherService(retrofit: Retrofit): WeatherService =
    retrofit.create(WeatherService::class.java)