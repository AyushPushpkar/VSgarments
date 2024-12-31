package com.example.vsgarments.authentication.util


// for better structure
sealed class Resource <T> (
    val data : T? = null ,
    val errorMassage : String? = null
){
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message : String) : Resource<T>(errorMassage = message)
    class Loading<T> : Resource<T>()
    class Unspecified<T> : Resource<T>()
}