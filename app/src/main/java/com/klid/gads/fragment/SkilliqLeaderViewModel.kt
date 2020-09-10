package com.klid.gads.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klid.gads.api.model.Skilliq
import com.klid.gads.repository.SkilliqRepository
import kotlinx.coroutines.*

/**
 * @author Ivan Kaptue (ivanokaptue@gmail.com)
 */
class SkilliqLeaderViewModel : ViewModel() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    private val skilliqRepository = SkilliqRepository()

    private val _skills: MutableLiveData<List<Skilliq>> by lazy {
        MutableLiveData<List<Skilliq>>().also {
            coroutineScope.launch {
                try {
                    val response = skilliqRepository.getTopSkilliq()
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
    val skills: LiveData<List<Skilliq>>
        get() = _skills

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

}