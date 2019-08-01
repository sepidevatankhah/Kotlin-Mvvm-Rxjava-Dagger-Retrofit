package de.joyn.myapplication.ui.photoDetail

import android.os.Bundle
import android.view.MenuItem
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.BaseDaggerActivity
import kotlinx.android.synthetic.main.activity_photo.*
import timber.log.Timber




class PhotoDetailActivity : BaseDaggerActivity<PhotoDetailViewState, PhotoDetailViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        createViewModel(PhotoDetailViewModel::class.java)
        //supportActionBar!!.setTitle("Your Activity Title"); // for set actionbar title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar
        bindBundle()
    }

    private fun bindBundle() {
        val extras = intent.extras
        Timber.d("extras : " + extras)
        val imageUrl = extras!!.getString("IMAGE_URL")
        val userName = extras!!.getString("USER_NAME")
        val tags = extras!!.getString("TAGS")
        val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
        imgLargePhoto.setImageURI(imgUri)
        Glide.with(applicationContext)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgLargePhoto)
        txtTags.text = tags
        txtUserName.text = userName
    }

    override fun handleState(state: PhotoDetailViewState) {
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}