package com.mazaj.task.network

import com.mazaj.task.view.ui.home.entity.NeoBrowseModel
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("browse")
    suspend fun browseData(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
        @Query("size") size: Int

    ): NeoBrowseModel

}