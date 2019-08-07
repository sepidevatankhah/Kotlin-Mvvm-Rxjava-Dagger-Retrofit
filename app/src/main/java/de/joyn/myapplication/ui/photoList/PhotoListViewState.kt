package de.joyn.myapplication.ui.photoList

import androidx.paging.PagedList
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.BaseViewState

class PhotoListViewState(val photoModels: PagedList<Models.PhotoResponse>) : BaseViewState() {
}