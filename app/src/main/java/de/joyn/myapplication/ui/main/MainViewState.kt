package de.joyn.myapplication.ui.main

import de.joyn.myapplication.domain.entity.FlowerModel
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.BaseViewState

class MainViewState(val flowerModel : Models.FlowerResponse) : BaseViewState() {
}