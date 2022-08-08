package com.example.truth_or_dare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.truth_or_dare.databinding.PlayerItemBinding
import com.example.truth_or_dare.models.Player

class PlayerListAdapter :
    ListAdapter<Player, PlayerListAdapter.PlayerListViewHolder>(DiffCallback) {

    class PlayerListViewHolder(private var binding: PlayerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) {
            binding.player = player
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListViewHolder {
        return PlayerListViewHolder(PlayerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
        val player = getItem(position)
        holder.bind(player)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.name == newItem.name
        }

    }
}