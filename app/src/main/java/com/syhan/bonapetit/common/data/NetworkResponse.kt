package com.syhan.bonapetit.common.data

sealed interface NetworkResponse {
    data object Success : NetworkResponse
    data object InternetConnectionError : NetworkResponse
    data object UnexpectedError : NetworkResponse
    data object Loading : NetworkResponse
}