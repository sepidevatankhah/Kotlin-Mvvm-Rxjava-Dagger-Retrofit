package de.joyn.myapplication.ui.photoList.photoViewholder

import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.recyclerview.BaseViewHolderAction

class PhotoViewHolderAction(adapterPosition: Int, var state: Int , var currentRowData : Models.PhotoResponse?) : BaseViewHolderAction(adapterPosition) {
    companion object {
        var SIMPLE_CLICK_STATE = 10

        fun createSelectAction(adapterPosition: Int , currentRowData : Models.PhotoResponse? ): PhotoViewHolderAction {
            return PhotoViewHolderAction(adapterPosition, SIMPLE_CLICK_STATE , currentRowData)
        }
    }
}