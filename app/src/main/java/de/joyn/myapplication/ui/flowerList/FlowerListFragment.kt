package de.joyn.myapplication.ui.flowerList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders


/**
 * This fragment shows the the status of the Flowers web services transaction.
 */
class FlowerListFragment : Fragment() {

    /**
     * Lazily initialize our [OverviewViewModel].
     */
    private val viewModel: FlowerViewModel by lazy {
        ViewModelProviders.of(this).get(FlowerViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
       // val binding = FlowerListFragmentBinding
    }
}