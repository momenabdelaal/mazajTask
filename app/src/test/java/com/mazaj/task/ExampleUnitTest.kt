package com.mazaj.task

import android.annotation.SuppressLint
import androidx.lifecycle.distinctUntilChanged
import com.mazaj.task.network.AppRepository
import com.mazaj.task.view.ui.home.HomeViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    private lateinit var viewModel: HomeViewModel


    @Before
    fun setUp() {

        viewModel = HomeViewModel(AppRepository())
    }

    @Test
    fun testApiBrowseData(){

        viewModel = HomeViewModel(AppRepository())
        viewModel.getBrowseData(0,20).value
    }


}