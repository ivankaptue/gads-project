package com.klid.gads.api

import com.klid.gads.api.model.Learner
import com.klid.gads.api.model.Skilliq
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author Ivan Kaptue (ivanokaptue@gmail.com)
 */

private const val BASE_URL = "https://gadsapi.herokuapp.com/api/"
private const val BASE_URL_FORM = "https://docs.google.com/forms/d/e/"

interface ApiService {

    @GET("hours")
    suspend fun getTopLearners(): Response<List<Learner>>

    @GET("skilliq")
    suspend fun getTopSkilliq(): Response<List<Skilliq>>

}

interface FormApiService {

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    suspend fun submitProject(
        @Field("entry.1824927963") email: String,
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.284483984") githubLink: String
    ): Response<Void>

}

object ApiFactory {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val apiService: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build().create(ApiService::class.java)

    val formApiService = Retrofit.Builder()
        .baseUrl(BASE_URL_FORM)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build().create(FormApiService::class.java)
}