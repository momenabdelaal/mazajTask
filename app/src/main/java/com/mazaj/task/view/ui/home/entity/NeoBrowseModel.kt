package com.mazaj.task.view.ui.home.entity

data class NeoBrowseModel(
    val links: Links,
    val near_earth_objects: List<NearEarthObject>,
    val page: Page
)