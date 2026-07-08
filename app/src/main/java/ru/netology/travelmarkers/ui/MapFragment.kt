package ru.netology.travelmarkers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ru.netology.travelmarkers.R
import ru.netology.travelmarkers.databinding.DialogMarkerBinding
import ru.netology.travelmarkers.databinding.FragmentMapBinding
import ru.netology.travelmarkers.dto.Marker
import ru.netology.travelmarkers.viewmodel.MarkerViewModel

class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MarkerViewModel by viewModels()
    private lateinit var googleMap: GoogleMap
    private val mapMarkers = mutableMapOf<com.google.android.gms.maps.model.Marker, Marker>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.fabList.setOnClickListener {
            findNavController().navigate(R.id.action_mapFragment_to_markersListFragment)
        }

        val args = arguments
        if (args != null) {
            val lat = args.getFloat("lat", 0f).toDouble()
            val lng = args.getFloat("lng", 0f).toDouble()
            if (lat != 0.0 && lng != 0.0) {
                if (::googleMap.isInitialized) {
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lng), 15f))
                }
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.isZoomControlsEnabled = true

        viewModel.data.observe(viewLifecycleOwner) { markers ->
            googleMap.clear()
            mapMarkers.clear()
            markers.forEach { marker ->
                val mapMarker = googleMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(marker.lat, marker.lng))
                        .title(marker.title)
                        .snippet(marker.description)
                )
                if (mapMarker != null) {
                    mapMarkers[mapMarker] = marker
                }
            }
        }

        googleMap.setOnMapLongClickListener { latLng ->
            showAddDialog(latLng)
        }

        googleMap.setOnMarkerClickListener { mapMarker ->
            val marker = mapMarkers[mapMarker]
            if (marker != null) {
                showEditDeleteDialog(marker)
            }
            true
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(55.75, 37.61), 10f))
    }

    private fun showAddDialog(latLng: LatLng) {
        val dialogBinding = DialogMarkerBinding.inflate(layoutInflater)
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.add_marker)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.save) { _, _ ->
                val title = dialogBinding.titleEdit.text.toString().trim()
                val description = dialogBinding.descriptionEdit.text.toString().trim()
                if (title.isNotEmpty()) {
                    viewModel.save(Marker(title = title, description = description, lat = latLng.latitude, lng = latLng.longitude))
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun showEditDeleteDialog(marker: Marker) {
        val dialogBinding = DialogMarkerBinding.inflate(layoutInflater)
        dialogBinding.titleEdit.setText(marker.title)
        dialogBinding.descriptionEdit.setText(marker.description)
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.edit_marker)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.save) { _, _ ->
                val title = dialogBinding.titleEdit.text.toString().trim()
                val description = dialogBinding.descriptionEdit.text.toString().trim()
                if (title.isNotEmpty()) {
                    viewModel.update(marker.id, title, description)
                }
            }
            .setNeutralButton(R.string.delete_marker) { _, _ ->
                viewModel.deleteById(marker.id)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
