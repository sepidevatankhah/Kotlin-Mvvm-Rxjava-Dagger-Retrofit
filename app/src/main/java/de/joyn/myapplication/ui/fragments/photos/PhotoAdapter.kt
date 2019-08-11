package de.joyn.myapplication.ui.fragments.photos

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import de.joyn.myapplication.network.dto.Models
import timber.log.Timber

typealias ClickListener = (Models.PhotoResponse) -> Unit

class PhotoAdapter(private val clickListener: ClickListener) :
    PagedListAdapter<Models.PhotoResponse, PhotoViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        Timber.d("Binding view holder at position $position")
        val photo = getItem(position)

        with(holder) {
            bind(photo)
            photo?.let {
                itemView.setOnClickListener {
                    clickListener(photo)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder.from(parent)

    companion object {
        /**
         * Callback for calculating the diff between two non-null items in a list.
         *
         * Used by ListAdapter or PagedListAdapter to calculate the minumum number of changes between and old list and a new
         * list that's been passed to `submitList`.
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Models.PhotoResponse>() {
            override fun areItemsTheSame(oldItem: Models.PhotoResponse, newItem: Models.PhotoResponse): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Models.PhotoResponse, newItem: Models.PhotoResponse): Boolean =
                oldItem == newItem
        }
    }
}