package com.example.vsgarments.authentication.util

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver


sealed class VerificationStatus {
    data object Idle : VerificationStatus()
    data object Loading : VerificationStatus()
    data object Success : VerificationStatus()
    data class Error(val message: String) : VerificationStatus()
}

// Define a custom Saver for the VerificationStatus class
val VerificationStatusSaver: Saver<VerificationStatus, *> = listSaver(
    save = { state ->
        when (state) {
            is VerificationStatus.Idle -> listOf(0)  // Idle state is represented by 0
            is VerificationStatus.Loading -> listOf(1)  // Loading state is represented by 1
            is VerificationStatus.Success -> listOf(2)  // Success state is represented by 2
            is VerificationStatus.Error -> listOf(3, state.message)  // Error state stores a message
        }
    },
    restore = { savedState ->
        when (savedState[0]) {
            0 -> VerificationStatus.Idle
            1 -> VerificationStatus.Loading
            2 -> VerificationStatus.Success
            3 -> VerificationStatus.Error(savedState[1] as String)
            else -> VerificationStatus.Idle  // Default state
        }
    }
)
