package de.joyn.myapplication.ui.fragments.photos

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.BaseViewState
import javax.inject.Inject

class PhotosViewState @Inject constructor( var pagedList : PagedList<Models.PhotoResponse>) : BaseViewState() {
}