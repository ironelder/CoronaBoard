package com.ironelder.coronamap

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ironelder.coronamap.model.CoronaBoardModel
import kotlinx.android.synthetic.main.item_corona_board.view.*

class CoronaBoardRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mCoronaBoardList = arrayListOf<CoronaBoardModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CoronaBoardItem(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_corona_board,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = mCoronaBoardList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = mCoronaBoardList[position]
        (holder as CoronaBoardItem).setItemData(model)
    }

    fun setItemList(list: List<CoronaBoardModel>) {
        mCoronaBoardList.clear()
        mCoronaBoardList.addAll(list)
        notifyDataSetChanged()
    }

    class CoronaBoardItem(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle = view.tv_item_title
        private val tvStatus = view.tv_item_status
        @SuppressLint("SetTextI18n")
        fun setItemData(data: CoronaBoardModel) {
            tvTitle.text = itemView.rootView.context.getString(itemView.rootView.context.resIdByName(data.code,"string"))
            tvStatus.text =
                itemView.rootView.context.getString(R.string.corona_board_subtitle_case) +
                    ": ${data.case} , " +
                    itemView.rootView.context.getString(R.string.corona_board_subtitle_death) +
                    ": ${data.death} , " +
                    itemView.rootView.context.getString(R.string.corona_board_subtitle_recovered) +
                        ": ${data.recovered}"
        }
    }
}