package ru.netology.travelmarkers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.travelmarkers.R
import ru.netology.travelmarkers.adapter.MarkerAdapter
import ru.netology.travelmarkers.databinding.FragmentMarkersListBinding
import ru.netology.travelmarkers.viewmodel.MarkerViewModel

class MarkersListFragment : Fragment() {

    private var _binding: FragmentMarkersListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MarkerViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMarkersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MarkerAdapter { marker ->
            val bundle = Bundle().apply {
                putFloat("lat", marker.lat.toFloat())
                putFloat("lng", marker.lng.toFloat())
            }
            findNavController().navigate(R.id.action_markersListFragment_to_mapFragment, bundle)
        }

        binding.recyclerView.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) { markers ->
            adapter.submitList(markers)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
