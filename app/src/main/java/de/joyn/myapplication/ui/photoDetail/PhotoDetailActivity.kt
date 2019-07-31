package de.joyn.myapplication.ui.photoDetail

import android.os.Bundle
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.BaseDaggerActivity

class PhotoDetailActivity : BaseDaggerActivity<PhotoDetailViewState, PhotoDetailViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flower)
        createViewModel(PhotoDetailViewModel::class.java)
    }

    override fun handleState(state: PhotoDetailViewState) {

    }
}