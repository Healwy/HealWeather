package com.xuniyishifanchen.healweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.xuniyishifanchen.healweather.logic.Repository
import com.xuniyishifanchen.healweather.logic.model.Place

class PlaceViewModel : ViewModel() {
    val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData) {
        Repository.searchPlaces(it)
    }
}