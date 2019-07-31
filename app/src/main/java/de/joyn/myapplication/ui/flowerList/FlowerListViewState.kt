package de.joyn.myapplication.ui.flowerList

import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.BaseViewState

class FlowerListViewState(val flowerModels: List<Models.FlowerResponse>) : BaseViewState() {
}