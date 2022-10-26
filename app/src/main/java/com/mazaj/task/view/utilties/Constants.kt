package com.mazaj.task.view.utilties

import android.annotation.SuppressLint
import com.mazaj.task.R


@SuppressLint("NewApi")
object Constants {



    const val SPLASH_DISPLAY_TIME = 7000
    //RequestsCodes
    var FILE_TYPE_IMAGE = 1001
    var FILE_TYPE_PDF = 1002
    var FILE_TYPE_AUDIO = 1003
    const val SUCCESS = 200
    const val FAILED = 401
    const val NOT_AUTOIZE = 403
    const val NOT_ACCEPTABLE = 406
    const val NOT_FOUND = 404
    const val NOT_ACTIVE = 405
    const val UNKNOWN_ERROR_SERVER = 500


    const val PERMISSION_STORAGE_IMAGES = 1315
    const val CHOOSE_FILE_REQUEST = 9544
    const val CAMERA_REQUEST = 1888
    const val MY_CAMERA_PERMISSION_CODE = 100



    const val CONTAINER_MAIN_ACTIVITY_ID = R.id.nav_host_fragment_content_main
    const val INTENT_PAGE = "fragment_name"
    const val INTENT_BUNDLE = "bundle_name"
    const val API_BASE_URL = "https://api.nasa.gov/neo/rest/v1/neo/"
    const val HOME_PAGE = 1







}