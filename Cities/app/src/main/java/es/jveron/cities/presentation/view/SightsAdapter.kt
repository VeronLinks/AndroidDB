package es.jveron.cities.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.jveron.cities.databinding.SightItemBinding
import es.jveron.cities.domain.model.Sight

class SightsAdapter : ListAdapter<Sight, SightsAdapter.SightViewHolder>(SightsDiffUtilCallback) {

    inner class SightViewHolder(val binding: SightItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SightViewHolder {
        val binding = SightItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SightViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SightViewHolder, position: Int) {
        val sight = getItem(position)
        holder.binding.sightName.text = sight.name
        holder.binding.sightDescription.text = sight.description
    }
}

object SightsDiffUtilCallback : DiffUtil.ItemCallback<Sight>() {
    override fun areItemsTheSame(oldItem: Sight, newItem: Sight): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Sight, newItem: Sight): Boolean {
        return oldItem.id == newItem.id
    }
}