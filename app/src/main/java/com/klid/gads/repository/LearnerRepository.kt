package com.klid.gads.repository

import com.klid.gads.api.ApiFactory
import com.klid.gads.api.model.Learner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * @author Ivan Kaptue (ivanokaptue@gmail.com)
 */
class LearnerRepository {

    suspend fun getTopLearners(): Response<List<Learner>> {
        return withContext(Dispatchers.IO) {
            ApiFactory.apiService.getTopLearners()
        }
    }

}