package com.klid.gads.repository

import com.klid.gads.api.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * @author Ivan Kaptue (ivanokaptue@gmail.com)
 */
class FormRepository {

    suspend fun submitProject(
        email: String,
        firstName: String,
        lastName: String,
        githubLink: String
    ): Response<Void> {
        return withContext(Dispatchers.IO) {
            ApiFactory.formApiService.submitProject(email, firstName, lastName, githubLink)
        }
    }

}