package com.example.alarstudios.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.alarstudios.R
import com.example.alarstudios.data.model.place.Place
import com.example.alarstudios.utils.getBitmapFromVectorDrawable
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject


@AndroidEntryPoint
class DetailsFragment @Inject constructor()
    : Fragment(R.layout.fragment_details) {

    private val args: DetailsFragmentArgs by navArgs()
    var myLocationIcon: PlacemarkMapObject? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            MapKitFactory.initialize(it)
        }
     }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val place = args.place
            showPlaceOnMap(place)
    }

    private fun showPlaceOnMap(place: Place) {
        nameTv.text = place.name
        countryTv.text = place.country

        mapView.map.move(
            CameraPosition(Point(place.lat, place.lon), defaultZoom, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0f),
            null)
        myLocationIcon = mapView.map.mapObjects.addPlacemark(Point(place.lat, place.lon))
        myLocationIcon!!.setIcon(
            ImageProvider.fromBitmap(context?.getBitmapFromVectorDrawable(R.drawable.ic_pinpoint))
        )
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    companion object {
        @JvmStatic
        fun newInstance(place: Place) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(PLACE_KEY, place)
            }
        }

        const val PLACE_KEY = "place"
        private const val defaultZoom = 13.0f
    }
}