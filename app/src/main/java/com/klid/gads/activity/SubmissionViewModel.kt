package com.klid.gads.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klid.gads.api.model.Learner
import com.klid.gads.repository.FormRepository
import com.klid.gads.repository.LearnerRepository
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.Exception

/**
 * @author Ivan Kaptue (ivanokaptue@gmail.com)
 */
class SubmissionViewModel : ViewModel() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    private val formRepository = FormRepository()

    fun submitProject(
        email: String,
        firstName: String,
        lastName: String,
        githubLink: String
    ): LiveData<Response<Void>> {
        val result = MutableLiveData<Response<Void>>()

        try {
            coroutineScope.launch {
                result.value = formRepository.submitProject(email, firstName, lastName, githubLink)
            }
        } catch (ex: Exception) {
            System.err.println(ex.message)
        }

        return result
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

}