package com.digging.coroutine.network.base

data class ErrorResponse(
    val errorBody: String
) : BaseErrorResponse()