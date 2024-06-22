package com.syhan.bonapetit.common.data

sealed interface ErrorType {
    data object ConnectionError : ErrorType
    data object UnexpectedError : ErrorType
}
