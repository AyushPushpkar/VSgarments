package com.example.vsgarments.authentication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(
    private val logoutRepository: LogoutRepository
) : ViewModel() {

    fun logout(): LiveData<Result<Unit>> {
        val resultLiveData = MutableLiveData<Result<Unit>>()

        viewModelScope.launch {
            try {
                val result = logoutRepository.clearPreferences()
                resultLiveData.postValue(result)
            } catch (e: Exception) {
                resultLiveData.postValue(Result.failure(e))
            }
        }
        return resultLiveData
    }

}

class LogoutRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val sharedPreferences by lazy {
        context.getSharedPreferences("AddressPrefs", Context.MODE_PRIVATE)
    }

    fun clearPreferences(): Result<Unit> {
        return try {
            sharedPreferences.edit().clear().apply()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
