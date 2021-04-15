package com.digging.coroutine.extensions

import com.digging.coroutine.network.base.ErrorResponse
import com.digging.coroutine.network.base.Outcome
import retrofit2.Response
import java.net.HttpURLConnection

fun <R: Any> Response<R>.parseResponse(): Outcome<R> {
    if (isSuccessful) {
        val body = body()

        if (body != null) {
            return Outcome.Success(value = body)
        }
    } else {
        return parseErrorBody(
            code = this.code(),
            errorBody = errorBody()?.string() ?: ""
        )
    }

    return Outcome.Failure(
        statusCode = HttpURLConnection.HTTP_INTERNAL_ERROR
    )
}

internal fun parseErrorBody(
    code: Int,
    errorBody: String
): Outcome.Failure {
    return try {
        Outcome.Failure(
            statusCode = code,
            baseErrorResponse = ErrorResponse(errorBody)
        )
    } catch (e: Exception) {
        Outcome.Failure(
            statusCode = HttpURLConnection.HTTP_INTERNAL_ERROR
        )
    }
}