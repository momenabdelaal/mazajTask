package com.mazaj.task.view.ui.home

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mazaj.task.databinding.FragmentHomeBinding
import com.mazaj.task.viewmodel.ViewModelFactory
import com.mazaj.task.network.Status
import com.mazaj.task.view.ui.home.adapter.BrowseDataAdapter
import com.mazaj.task.view.ui.home.entity.NearEarthObject
import com.mazaj.task.view.utilties.OnItemClick
import com.mazaj.task.view.utilties.createProgressDialog
import com.mazaj.task.view.utilties.pagination.EndlessRecyclerViewScrollListener


class HomeFragment : Fragment() {

    private var homeBinding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = homeBinding!!
    lateinit var scrollListener: EndlessRecyclerViewScrollListener
    var browseDataAdapter: BrowseDataAdapter? = null
    var mDialog: ProgressDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()




    }

    private fun getBrowseData(page: Int, size: Int) {
        mDialog = createProgressDialog(context)
        val homeViewModel =
            ViewModelProvider(this, ViewModelFactory())[HomeViewModel::class.java]

        homeViewModel.getBrowseData(page, size).observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { data ->
                            mDialog!!.dismiss()

                            Log.d("data", "data: " + data.page)

                            if (data.near_earth_objects.isNotEmpty()) {
                                if (page == 0) browseDataAdapter?.reset()
                                browseDataAdapter?.addList(data.near_earth_objects)
                            } else {
                                Toast.makeText(context, "no data found", Toast.LENGTH_LONG)
                                    .show()

                            }


                        }
                    }
                    Status.ERROR -> {
                        mDialog!!.dismiss()
                        Log.d("error", "err: ")

                    }
                    Status.LOADING -> {
                        Log.d("loading", "loading")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeBinding = null
    }

    private fun setRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvItems.layoutManager = linearLayoutManager
        binding.rvItems.itemAnimator = DefaultItemAnimator()
        browseDataAdapter = BrowseDataAdapter()
        binding.rvItems.adapter = browseDataAdapter


        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                var clickCount = page
                clickCount++
                getBrowseData(clickCount, 20)
            }

        }
        getBrowseData(0, 20)


        binding.rvItems.addOnScrollListener(scrollListener)
        browseDataAdapter!!.onItemClickListener = object : OnItemClick {
            override fun onItemClick(view: View, position: Int) {


            }

        }
    }
}