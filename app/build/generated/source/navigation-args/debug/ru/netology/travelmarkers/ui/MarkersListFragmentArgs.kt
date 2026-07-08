package ru.netology.travelmarkers.ui

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Float
import kotlin.jvm.JvmStatic

public data class MarkersListFragmentArgs(
  public val lat: Float = 0fF,
  public val lng: Float = 0fF,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putFloat("lat", this.lat)
    result.putFloat("lng", this.lng)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("lat", this.lat)
    result.set("lng", this.lng)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): MarkersListFragmentArgs {
      bundle.setClassLoader(MarkersListFragmentArgs::class.java.classLoader)
      val __lat : Float
      if (bundle.containsKey("lat")) {
        __lat = bundle.getFloat("lat")
      } else {
        __lat = 0fF
      }
      val __lng : Float
      if (bundle.containsKey("lng")) {
        __lng = bundle.getFloat("lng")
      } else {
        __lng = 0fF
      }
      return MarkersListFragmentArgs(__lat, __lng)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): MarkersListFragmentArgs {
      val __lat : Float?
      if (savedStateHandle.contains("lat")) {
        __lat = savedStateHandle["lat"]
        if (__lat == null) {
          throw IllegalArgumentException("Argument \"lat\" of type float does not support null values")
        }
      } else {
        __lat = 0fF
      }
      val __lng : Float?
      if (savedStateHandle.contains("lng")) {
        __lng = savedStateHandle["lng"]
        if (__lng == null) {
          throw IllegalArgumentException("Argument \"lng\" of type float does not support null values")
        }
      } else {
        __lng = 0fF
      }
      return MarkersListFragmentArgs(__lat, __lng)
    }
  }
}
