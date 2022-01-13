package es.jveron.cities.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.jveron.cities.databinding.CityItemBinding
import es.jveron.cities.domain.model.City

class CitiesAdapter : ListAdapter<City, CitiesAdapter.CityViewHolder>(CitiesDiffUtilCallback) {

    inner class CityViewHolder(val binding: CityItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = getItem(position)
        holder.binding.cityName.text = city.name
        holder.binding.cityDescription.text = city.description
    }
}

object CitiesDiffUtilCallback : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.id == newItem.id
    }
}