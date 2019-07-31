package de.joyn.myapplication.ui.flowerList.flowerViewholder

import de.joyn.myapplication.ui.base.recyclerview.BaseViewHolderAction
import java.text.FieldPosition

class FlowerViewHolderAction(adapterPosition: Int, var state: Int) : BaseViewHolderAction(adapterPosition) {
    companion object {
        var SIMPLE_CLICK_STATE = 10

        fun createSelectAction(adapterPosition: Int): FlowerViewHolderAction {
            return FlowerViewHolderAction(adapterPosition, SIMPLE_CLICK_STATE)
        }
    }
}