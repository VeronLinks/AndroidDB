package es.jveron.cities.presentation.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.jveron.cities.R
import es.jveron.cities.databinding.FragmentHomeBinding
import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.model.CityFilter
import es.jveron.cities.presentation.viewmodel.CityState
import es.jveron.cities.presentation.viewmodel.HomeViewModel
import es.jveron.cities.presentation.viewmodel.HomeViewModelFactory
import kotlinx.coroutines.flow.collect

class HomeFragment : Fragment(), OnClickCityListener {

    var _binding: FragmentHomeBinding? = null
    val binding: FragmentHomeBinding get() = _binding!!

    val homeViewModel : HomeViewModel by viewModels {
        HomeViewModelFactory(requireContext())
    }

    val citiesAdapter = CitiesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.citiesToolbar.inflateMenu(R.menu.cities_menu)
        binding.citiesToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_all_cities -> {
                    homeViewModel.setFilter(CityFilter.ALL_CITIES)
                    true
                }
                R.id.action_sunny_cities -> {
                    homeViewModel.setFilter(CityFilter.SUNNY_CITIES)
                    true
                }
                R.id.action_cloudy_cities -> {
                    homeViewModel.setFilter(CityFilter.CLOUDY_CITIES)
                    true
                }
                else -> false
            }
        }

        childFragmentManager.setFragmentResultListener(ADD_CITY_REQUEST_KEY, viewLifecycleOwner) { _, bundle ->
            val city : City? = bundle.getParcelable(CITY_KEY) as? City
            homeViewModel.addCity(city)
        }

        binding.addCityButton.setOnClickListener {
            val addCityFragment = AddCityFragment()
            addCityFragment.show(childFragmentManager, ADD_CITY_TAG)
        }

        binding.citiesRecyclerView.apply {
            adapter = citiesAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.citiesStateFlow.collect { cityState: CityState ->
                setState(cityState)
            }
        }

        homeViewModel.getData()

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            homeViewModel.filterCityState.collect { filter ->
                setFilter(filter)
            }
        }

        homeViewModel.getFilter()
    }

    private fun setFilter(filter: CityFilter) {
        val allCities : MenuItem = binding.citiesToolbar.menu.findItem(R.id.action_all_cities)
        val sunnyCities : MenuItem = binding.citiesToolbar.menu.findItem(R.id.action_sunny_cities)
        val cloudyCities : MenuItem = binding.citiesToolbar.menu.findItem(R.id.action_cloudy_cities)

        reset(allCities, sunnyCities, cloudyCities)
        when (filter) {
            CityFilter.ALL_CITIES -> {
                setSelected(allCities)
            }
            CityFilter.SUNNY_CITIES -> {
                setSelected(sunnyCities)
            }
            CityFilter.CLOUDY_CITIES -> {
                setSelected(cloudyCities)
            }
        }
    }

    private fun setSelected(menuItem: MenuItem) {
        menuItem.icon.setTint(Color.YELLOW)
    }

    private fun reset(vararg menuItems: MenuItem) {
        menuItems.forEach {
            it.icon.setTint(Color.WHITE)
        }
    }

    private fun setState(cityState: CityState) {
        when (cityState) {
            CityState.Loading -> binding.progressBar.visibility = View.VISIBLE
            is CityState.Success -> {
                binding.progressBar.visibility = View.GONE
                val citiesList = cityState.data
                citiesAdapter.submitList(citiesList)
            }
            is CityState.Failure -> {
                Toast.makeText(context, "Failure!", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCityClicked(cityId: Int) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, CityAndSightsFragment.newInstance(cityId))
            .addToBackStack(null)
            .commit()
    }
}