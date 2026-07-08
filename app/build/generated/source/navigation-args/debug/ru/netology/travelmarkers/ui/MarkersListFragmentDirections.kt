package ru.netology.travelmarkers.ui

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import ru.netology.travelmarkers.R

public class MarkersListFragmentDirections private constructor() {
  public companion object {
    public fun actionMarkersListFragmentToMapFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_markersListFragment_to_mapFragment)
  }
}
