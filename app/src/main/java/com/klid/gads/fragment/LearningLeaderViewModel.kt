package com.klid.gads.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klid.gads.api.model.Learner
import com.klid.gads.repository.LearnerRepository
import kotlinx.coroutines.*

/**
 * @author Ivan Kaptue (ivanokaptue@gmail.com)
 */
class LearningLeaderViewModel : ViewModel() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    private val learnerRepository = LearnerRepository()

    private val _learners: MutableLiveData<List<Learner>> by lazy {
        MutableLiveData<List<Learner>>().also {
            coroutineScope.launch {
                try {
                    val response = learnerRepository.getTopLearners()
                    if (response.isSuccessful) {
                        it.value = response.body()
                    } else {
                        System.err.println("Error occur")
                    }
                } catch (ex: Exception) {
                    System.err.println(ex.message)
                }
            }
        }
    }
    val learners: LiveData<List<Learner>>
        get() = _learners

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

}