package de.joyn.myapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.joyn.myapplication.R
import de.joyn.myapplication.ui.base.BaseDaggerActivity

class MainActivity : BaseDaggerActivity<MainViewState , MainViewModel>() {
    override fun handleState(state: MainViewState) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
