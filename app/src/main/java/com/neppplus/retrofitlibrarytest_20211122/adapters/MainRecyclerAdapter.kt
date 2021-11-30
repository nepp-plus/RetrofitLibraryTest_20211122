package com.neppplus.retrofitlibrarytest_20211122.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MainRecyclerAdapter : RecyclerView.Adapter< RecyclerView.ViewHolder >() {

//    2가지 ViewHolder 필요함. => 0번칸 : 상단(Header)부 xml  / 나머지(Item)칸 : 리뷰모양 xml

    inner class HeaderViewHolder(row: View) : RecyclerView.ViewHolder(row) {

    }

    inner class ItemViewHolder(row: View) : RecyclerView.ViewHolder(row) {

    }


}