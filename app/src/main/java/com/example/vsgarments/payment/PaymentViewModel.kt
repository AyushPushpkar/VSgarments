package com.example.vsgarments.payment_methods

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(): ViewModel(),PaymentResultListener {

    private val _paymentStatus = MutableStateFlow<PaymentState>(PaymentState.Idle)
    val paymentStatus : StateFlow<PaymentState> = _paymentStatus

    fun initiatePayment(activity: Activity,amount:String){
        viewModelScope.launch {
            val checkout = Checkout()
            checkout.setKeyID("rzp_test_QMwt6X5FBy9WXe")

            val paymentDetails = JSONObject().apply {
                put("name", "Vs Garments")
                put("email", "aryankumarchallenger@gmail.com")
                put("description", "Test Payment")
                put("currency", "INR")
                put("amount", (amount.toFloat() * 100).toInt()) // Convert to paise
                put("prefill.contact", "9284064503")
            }
            try {
                _paymentStatus.value = PaymentState.Processing
                checkout.open(activity, paymentDetails)
            }catch (e: Exception) {
                e.printStackTrace()
                _paymentStatus.value = PaymentState.Failure(e.localizedMessage ?: "Unknown error")
            }
        }

    }
    override fun onPaymentSuccess(paymentId: String?) {
        _paymentStatus.value = PaymentState.Success(paymentId ?: "Unknown Payment ID")

        //updating the ui
        viewModelScope.launch {
            delay(3000) // show access message
            _paymentStatus.value = PaymentState.Idle
        }
    }

    override fun onPaymentError(errorCode: Int, errorMessage: String?) {
        _paymentStatus.value = PaymentState.Failure(errorMessage ?: "Payment Failed")

        //updating the ui
        viewModelScope.launch {
            delay(3000) // show access message
            _paymentStatus.value = PaymentState.Idle}
    }
    fun resetPaymentState(){
       _paymentStatus.value = PaymentState.Idle
    }
}




// Payment State for ui update
sealed class PaymentState {
    object Idle : PaymentState()
    object Processing : PaymentState()
    data class Success(val paymentId: String) : PaymentState()
    data class Failure(val errorMessage: String) : PaymentState()
}