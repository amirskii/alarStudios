package com.example.alarstudios.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.alarstudios.R
import com.example.alarstudios.data.model.place.Place
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_item.view.*

class PlaceAdapter(
    private val context: Context,
    private val delegate: Delegate
): PagingDataAdapter<Place, PlaceAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindData(it)
        }
    }

    companion object {
        val imageUri = "https://i.imgur.com/tGbaZCY.jpg"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Place>() {
            override fun areItemsTheSame(oldItem: Place, newItem: Place) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Place, newItem: Place) = oldItem == newItem
        }
    }

    inner class ViewHolder internal constructor(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_item, parent, false)),
        View.OnClickListener {

        private lateinit var item: Place
        private val nameTv: TextView = itemView.nameTv
        private val countryTv: TextView = itemView.countryTv
        private val imageIv: ImageView = itemView.imageIv

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            delegate.onItemClick(this.item)
        }

        fun bindData(item: Place) {
            this.item = item
            nameTv.text = item.name
            countryTv.text = item.country
            Picasso.with(context).load(imageUri).into(imageIv)
        }
    }

//    fun setValue(newValue: Weather) {
//        if (mValues.isEmpty()) {
//            mValues.add(newValue)
//            notifyItemInserted(mValues.size - 1)
//        } else {
//            mValues[0] = newValue
//            notifyItemChanged(0)
//        }
//    }

    interface Delegate {
        fun onItemClick(item: Place)
    }

}