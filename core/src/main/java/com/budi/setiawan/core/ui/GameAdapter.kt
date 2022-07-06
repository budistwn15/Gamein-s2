package com.budi.setiawan.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.budi.setiawan.core.databinding.ItemGamesBinding
import com.budi.setiawan.core.domain.model.Game
import com.budi.setiawan.core.utils.DateFormatter
import com.bumptech.glide.Glide

class GameAdapter : RecyclerView.Adapter<GameAdapter.ListViewHolder>() {

    private var listData: MutableList<Game> = mutableListOf()
    private var onItemClick: ((Game) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Game>){
        with(this.listData){
            clear()
            addAll(newListData)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            ItemGamesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(private val binding: ItemGamesBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Game){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.background_image)
                    .into(ivImage)
                Glide.with(itemView.context)
                    .load(data.background_image)
                    .circleCrop()
                    .into(ivImageSmall)

                tvTitle.text = data.name
                tvRating.text = data.rating.toString()
                tvReleased.text = DateFormatter.formatDate(data.released)
                tvGenre.text = data.genres
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[layoutPosition])
            }
        }
    }

    fun setOnItemClickListener(listener: ((Game) -> Unit)){
        onItemClick = listener
    }

}