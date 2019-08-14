package de.joyn.myapplication.ui.fragments.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.joyn.myapplication.databinding.ItemPhotoBinding
import de.joyn.myapplication.network.dto.Models

class PhotoViewHolder private constructor(private val binding: ItemPhotoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Models.PhotoResponse? , clickListener : PhotoClickListener) {
        binding.photo = photo
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): PhotoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPhotoBinding.inflate(layoutInflater, parent, false)
            return PhotoViewHolder(binding)
        }
    }

}