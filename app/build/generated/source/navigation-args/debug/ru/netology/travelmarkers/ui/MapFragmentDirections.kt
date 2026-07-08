package ru.netology.travelmarkers.ui

import android.os.Bundle
import androidx.navigation.NavDirections
import kotlin.Float
import kotlin.Int
import ru.netology.travelmarkers.R

public class MapFragmentDirections private constructor() {
  private data class ActionMapFragmentToMarkersListFragment(
    public val lat: Float = 0fF,
    public val lng: Float = 0fF,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_mapFragment_to_markersListFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putFloat("lat", this.lat)
        result.putFloat("lng", this.lng)
        return result
      }
  }

  public companion object {
    public fun actionMapFragmentToMarkersListFragment(lat: Float = 0fF, lng: Float = 0fF):
        NavDirections = ActionMapFragmentToMarkersListFragment(lat, lng)
  }
}
