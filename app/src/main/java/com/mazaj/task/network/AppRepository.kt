package com.mazaj.task.network

import com.mazaj.task.view.ui.home.entity.NeoBrowseModel
import com.mazaj.task.view.utilties.Constants

class AppRepository {

    suspend fun getBrowseData(page: Int,size: Int): NeoBrowseModel =
        ConnectionRetrofit.apis.browseData(
           Constants.API_TOKEN,
            page,size
        )


}