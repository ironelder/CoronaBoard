package com.ironelder.coronamap

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ironelder.coronamap.model.CoronaBoardModel
import com.ironelder.coronamap.model._CoronaBoardModel
import kotlinx.android.synthetic.main.header_corona_board.view.*
import kotlinx.android.synthetic.main.item_corona_board.view.*

class CoronaBoardRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mCoronaBoardList = arrayListOf<_CoronaBoardModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == BOARD_TYPE_HEADER){
            CoronaBoardHeader(LayoutInflater.from(parent.context).inflate(R.layout.header_corona_board, parent, false))
        } else {
            CoronaBoardItem(LayoutInflater.from(parent.context).inflate(R.layout.item_corona_board, parent, false))
        }
    }

    override fun getItemCount(): Int = mCoronaBoardList.size

    override fun getItemViewType(position: Int): Int = mCoronaBoardList[position].BoardType

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is CoronaBoardHeader){
            holder.setItemData(mCoronaBoardList[position])
        } else {
            (holder as CoronaBoardItem).setItemData(mCoronaBoardList[position])
        }
    }

    fun setItemList(list:ArrayList<_CoronaBoardModel>){
        mCoronaBoardList.clear()
        mCoronaBoardList.addAll(list)
        notifyDataSetChanged()
    }

    class CoronaBoardItem(view:View) : RecyclerView.ViewHolder(view){
        private val tvTitle = view.tv_item_title
        @SuppressLint("SetTextI18n")
        fun setItemData(data:_CoronaBoardModel){
            tvTitle.text = "${data.Country} : ${data.Count}"
        }
    }

    class CoronaBoardHeader(view:View) : RecyclerView.ViewHolder(view){
        private val tvTitle = view.tv_header_title
        @SuppressLint("SetTextI18n")
        fun setItemData(data:_CoronaBoardModel){
            tvTitle.text = "${data.Country}"
        }
    }
}