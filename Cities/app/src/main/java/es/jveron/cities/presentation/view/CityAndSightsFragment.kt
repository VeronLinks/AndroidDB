package es.jveron.cities.presentation.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.jveron.cities.R
import es.jveron.cities.databinding.FragmentCityAndSightsBinding
import es.jveron.cities.presentation.viewmodel.*
import kotlinx.coroutines.flow.collect

class CityAndSightsFragment(val cityId: Int) : Fragment() {

    var _binding: FragmentCityAndSightsBinding? = null
    val binding: FragmentCityAndSightsBinding get() = _binding!!

    val cityAndSightsViewModel : CityAndSightsViewModel by viewModels {
        CityAndSightsViewModelFactory(requireContext(), cityId)
    }

    val sightsAdapter = SightsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCityAndSightsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cityAndSightsRecyclerView.apply {
            adapter = sightsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            cityAndSightsViewModel.sightsStateFlow.collect { sightState: SightState ->
                setState(sightState)
            }
        }

        cityAndSightsViewModel.getData()
    }

    private fun setState(sightState: SightState) {
        when (sightState) {
            SightState.Loading -> binding.progressBar.visibility = View.VISIBLE
            is SightState.Success -> {
                binding.progressBar.visibility = View.GONE
                val cityAndSights = sightState.data
                sightsAdapter.submitList(cityAndSights.sights)
                binding.cityName.text = cityAndSights.city.name
            }
            is SightState.Failure -> {
                Toast.makeText(context, "Failure!", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    companion object {
        fun newInstance(cityId: Int) = CityAndSightsFragment(cityId)
    }
}