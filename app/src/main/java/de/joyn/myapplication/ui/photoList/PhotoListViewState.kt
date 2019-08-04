package de.joyn.myapplication.ui.photoList

import androidx.paging.PagedList
import de.joyn.myapplication.data.repository.NetworkState
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.BaseViewState

class PhotoListViewState(var photoModels: PagedList<Models.PhotoResponse>//,
                         //var networkState: NetworkState
) : BaseViewState() {
}