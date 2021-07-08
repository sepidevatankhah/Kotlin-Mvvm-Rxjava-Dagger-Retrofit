package de.joyn.myapplication.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import de.joyn.myapplication.di.viewmodel.ViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment<M, VM : BaseViewModel<M>> : DaggerFragment() {

    @Inject
    //@JvmField
    lateinit var viewModelFactory: ViewModelFactory
    val compositeDisposable: CompositeDisposable = CompositeDisposable()


    protected lateinit var viewModel: VM


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onCreateCompleted();
    }

    /**
     * add your code here evry thing injected and view
     */
    protected abstract fun onCreateCompleted()


    @LayoutRes
    abstract fun getLayout(): Int

    override fun onStart() {
        super.onStart()
        startObserving()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }


    fun createViewModel(clazz: Class<VM>) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(clazz)
    }


    fun showToast(@StringRes string: Int) {
        if (string != 0)
            Toast.makeText(activity, getString(string), Toast.LENGTH_LONG).show()
    }


    protected fun startObserving() {
        viewModel.stateLiveData.observe(this, Observer { state -> handleState(state) })
    }

    abstract fun handleState(state: M);

    fun showMessage(message: String) = Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}