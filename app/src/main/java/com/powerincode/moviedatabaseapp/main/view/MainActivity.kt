package com.powerincode.moviedatabaseapp.main.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.powerincode.core.ui.activity.BaseActivity
import com.powerincode.moviedatabaseapp.R
import com.powerincode.moviedatabaseapp.extensions.getApplicationComponent
import com.powerincode.moviedatabaseapp.main.adapter.PopularMoviesRecyclerViewAdapter
import com.powerincode.moviedatabaseapp.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@UseExperimental(ExperimentalCoroutinesApi::class)
class MainActivity : BaseActivity<MainViewModel>(R.layout.activity_main, MainViewModel::class.java) {

    private val popularMovieAdapter = PopularMoviesRecyclerViewAdapter()

    override fun inject() {
        getApplicationComponent().mainActivityComponent()
            .create(this)
            .inject(this)
    }

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(popular_movie_recyclerview) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = popularMovieAdapter
        }

        viewModel.movies.observe(this, Observer {
            popularMovieAdapter.swapData(it)
        })
    }

}
