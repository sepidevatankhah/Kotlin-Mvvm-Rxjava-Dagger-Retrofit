package de.joyn.myapplication.ui.flowerList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.joyn.myapplication.network.dto.Models

class FlowerViewModel : ViewModel() {

    // Internally, we use a MutableLiveData, because we will be updating the List of Flowers
    // with new values
    private val _properties = MutableLiveData<List<Models.FlowerResponse>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<List<Models.FlowerResponse>>
        get() = _properties
}