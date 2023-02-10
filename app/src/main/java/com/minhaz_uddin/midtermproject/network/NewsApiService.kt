package com.minhaz_uddin.midtermproject.network

import com.minhaz_uddin.midtermproject.model.Article
import com.minhaz_uddin.midtermproject.model.Constants.Constants.API_KEY
import com.minhaz_uddin.midtermproject.model.NewsData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL="https://newsapi.org/v2/"

private val moshi=Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit=Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
    BASE_URL).build()


interface NewsApiService{
    @GET("top-headlines")
    suspend fun getAllNews(@Query("category") category: String?, @Query("country") countryCode: String = "us", @Query("apiKey") apiKey: String = API_KEY): NewsData


}

object NewsApi{
    val retrofitService:NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}