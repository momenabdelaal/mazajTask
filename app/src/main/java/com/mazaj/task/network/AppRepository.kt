package com.mazaj.task.network

import com.mazaj.task.view.ui.home.entity.NeoBrowseModel

class AppRepository {

    suspend fun getBrowseData(page: Int,size: Int): NeoBrowseModel =
        ConnectionRetrofit.apis.browseData(
           "iAhjOi5eXBoPJhNFi74d7DrZ3ckv7AulNJjMWKhK",
            page,size
        )


}