package ru.netology.travelmarkers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.travelmarkers.databinding.ItemMarkerBinding
import ru.netology.travelmarkers.dto.Marker

class MarkerAdapter(
    private val onClick: (Marker) -> Unit
) : ListAdapter<Marker, MarkerAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMarkerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemMarkerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marker: Marker) {
            binding.title.text = marker.title
            binding.description.text = marker.description
            binding.root.setOnClickListener { onClick(marker) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Marker>() {
        override fun areItemsTheSame(oldItem: Marker, newItem: Marker) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Marker, newItem: Marker) = oldItem == newItem
    }
}
