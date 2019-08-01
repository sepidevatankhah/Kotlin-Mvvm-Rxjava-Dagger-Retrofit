package de.joyn.myapplication.ui.photoList.fragment

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import de.joyn.myapplication.ui.base.BaseDaggerFragment


//@Module
//class PhotoFragmentModule {
//
//    @Provides
//    fun providerLayoutManager(fragment: Fragment): LinearLayoutManager {
//        return LinearLayoutManager(fragment.context, LinearLayoutManager.HORIZONTAL, false)
//    }
//
////    @Binds
////    @IntoMap
////    @IntKey(LeaderBoardRecyclerViewAdaptor2.TYPE_WEEK)
////    internal abstract fun bindLeaderBoardViewHolder(factory: LeaderBoardRankingVHFactory): BaseViewModelFactory
//
//}