package com.digging.coroutine.network

import com.digging.coroutine.network.model.ListNewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface NewsNetwork {
    @GET("{type}")
    suspend fun getNews(
        @Path("type") type: String = "everything",
        @Query("q") q: String
    ): Response<ListNewsResponse>
}