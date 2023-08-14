package com.example.interviewapplication.ui.travel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewapplication.BR
import com.example.interviewapplication.data.Data
import com.example.interviewapplication.databinding.ViewHolderTravelBinding

class TravelPagingAdapter : PagingDataAdapter<Data, TravelPagingAdapter.TravelViewHolder>(diff_util) {

    var onClick : ((Data) -> Unit) ? = null

    companion object{
        val diff_util = object : DiffUtil.ItemCallback<Data>(){
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun onTravelItemClick(listener: (Data) -> Unit){
        onClick = listener
    }

    inner class TravelViewHolder(val viewDataBinding: ViewHolderTravelBinding) : RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onBindViewHolder(holder: TravelViewHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.travel_data, getItem(position))
        holder.viewDataBinding.root.setOnClickListener{
            onClick?.let {
                it(getItem(position)!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelViewHolder {
        val binding = ViewHolderTravelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TravelViewHolder(binding)
    }
}