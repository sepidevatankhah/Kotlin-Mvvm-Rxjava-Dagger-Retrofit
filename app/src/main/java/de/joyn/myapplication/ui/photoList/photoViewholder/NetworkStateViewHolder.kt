package de.joyn.myapplication.ui.photoList.photoViewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.joyn.myapplication.R
import de.joyn.myapplication.data.repository.NetworkState
import de.joyn.myapplication.data.repository.Status
import kotlinx.android.synthetic.main.item_network_state.view.*

class NetworkStateViewHolder(val view: View, private val retryCallback: () -> Unit) : RecyclerView.ViewHolder(view) {

    init {
        itemView.btnRetryLoading.setOnClickListener { retryCallback() }
    }

    fun bindTo(networkState: NetworkState?) {
        //error message
        itemView.txtErrorMessage.visibility = if (networkState?.message != null) View.VISIBLE else View.GONE
        if (networkState?.message != null) {
            itemView.txtErrorMessage.text = networkState.message
        }

        //loading and retry
        itemView.btnRetryLoading.visibility = if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
        itemView.progressBarLoading.visibility = if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_network_state, parent, false)
            return NetworkStateViewHolder(view, retryCallback)
        }
    }

}