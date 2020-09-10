package com.klid.gads.repository

import com.klid.gads.api.ApiFactory
import com.klid.gads.api.model.Skilliq
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * @author Ivan Kaptue (ivanokaptue@gmail.com)
 */
class SkilliqRepository {

    suspend fun getTopSkilliq(): Response<List<Skilliq>> {
        return withContext(Dispatchers.IO) {
            ApiFactory.apiService.getTopSkilliq()
        }
    }

}