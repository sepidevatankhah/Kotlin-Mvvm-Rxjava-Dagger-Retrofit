package de.joyn.myapplication.ui.fragments.photoDetail

import de.joyn.myapplication.domain.entity.PhotoModel
import de.joyn.myapplication.ui.base.BaseViewState
import javax.inject.Inject

class PhotoDetailState @Inject constructor(var model : PhotoModel) : BaseViewState() {

}
