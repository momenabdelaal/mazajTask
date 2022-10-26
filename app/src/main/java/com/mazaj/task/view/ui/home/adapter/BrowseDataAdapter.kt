package com.mazaj.task.view.ui.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mazaj.task.R
import com.mazaj.task.databinding.ItemBrowseDataBinding
import com.mazaj.task.view.ui.home.entity.NearEarthObject
import com.mazaj.task.view.utilties.OnItemClick


class BrowseDataAdapter : RecyclerView.Adapter<BrowseDataAdapter.RequestOneHolder>() {


    var dataList: MutableList<NearEarthObject> = ArrayList()

    var onItemClickListener: OnItemClick? = null


    var context: Context? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RequestOneHolder {

        val binding: ItemBrowseDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.item_browse_data, viewGroup, false
        )
        context = viewGroup.context
        return RequestOneHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RequestOneHolder, i: Int) {
        val model = dataList[i]

            viewHolder.binding.tvTitle.text = model.name




        if (onItemClickListener != null) {
            viewHolder.binding.linearItem.setOnClickListener { v1 ->
                onItemClickListener!!.onItemClick(v1, i)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addList(list: List<NearEarthObject>?) {
        dataList.addAll(list!!)
        notifyDataSetChanged()
    }

    fun reset() {
        dataList.clear()
        notifyDataSetChanged()
    }

    val list: List<NearEarthObject>
        get() = dataList

    class RequestOneHolder(var binding: ItemBrowseDataBinding) : RecyclerView.ViewHolder(
        binding.root
    )

}