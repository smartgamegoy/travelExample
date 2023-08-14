package com.example.interviewapplication.ui.information

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denzcoskun.imageslider.models.SlideModel
import com.example.interviewapplication.data.Data
import com.example.interviewapplication.utils.Constants

class TravelInformationViewModel : ViewModel() {

    val imageList = MutableLiveData<ArrayList<SlideModel>>() // Create image list
    val itemData = ObservableField<Data>()

    val telTitle = ObservableField<String>()
    val addressTitle = ObservableField<String>()

    init {
        telTitle.set(Constants.tel)
        addressTitle.set(Constants.address)
    }
}