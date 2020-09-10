package com.klid.gads.api

import com.klid.gads.api.model.Learner
import com.klid.gads.api.model.Skilliq
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * @author Ivan Kaptue (ivanokaptue@gmail.com)
 */

private const val BASE_URL = "https://gadsapi.herokuapp.com/api/"


interface ApiService {

    @GET("hours")
    suspend fun getTopLearners(): Response<List<Learner>>

    @GET("skilliq")
    suspend fun getTopSkilliq(): Response<List<Skilliq>>

}

object ApiFactory {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val apiService: ApiService = retrofit().create(ApiService::class.java)
}