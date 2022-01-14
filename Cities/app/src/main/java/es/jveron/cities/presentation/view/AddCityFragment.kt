package es.jveron.cities.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import es.jveron.cities.databinding.FragmentAddCityBinding
import es.jveron.cities.domain.model.City

const val ADD_CITY_TAG = "AddCityTag"
const val ADD_CITY_REQUEST_KEY = "AddCityRequestKey"
const val CITY_KEY = "CityKey"

class AddCityFragment: DialogFragment() {

    var _binding : FragmentAddCityBinding? = null
    val binding : FragmentAddCityBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            val city = getData()
            val bundle = Bundle()
            bundle.putParcelable(CITY_KEY, city)
            setFragmentResult(ADD_CITY_REQUEST_KEY, bundle)
            dismiss()
        }
    }

    private fun getData() : City{
        val name = binding.nameEditText.text.toString()
        val description = binding.descriptionEditText.text.toString()
        val sunshineHours = binding.sunshineHoursEditText.text.toString().toIntOrNull() ?: 0
        return City(
            id = System.currentTimeMillis().toInt(),
            name = name,
            description = description,
            sunshineHours = sunshineHours)
    }

}