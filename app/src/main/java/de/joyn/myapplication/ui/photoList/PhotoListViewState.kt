package de.joyn.myapplication.ui.photoList

import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.BaseViewState

class PhotoListViewState(val flowerModels: List<Models.FlowerResponse>) : BaseViewState() {
}