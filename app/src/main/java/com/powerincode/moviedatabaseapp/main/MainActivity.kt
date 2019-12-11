package com.powerincode.moviedatabaseapp.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.powerincode.core.domain.repositories.Data
import com.powerincode.core.ui.activity.BaseActivity
import com.powerincode.domain.repositories.MovieRepository
import com.powerincode.moviedatabaseapp.R
import com.powerincode.moviedatabaseapp.extensions.extractData
import com.powerincode.moviedatabaseapp.extensions.getApplicationComponent
import com.powerincode.moviedatabaseapp.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var repository: MovieRepository
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getApplicationComponent().mainActivityComponent()
            .create(this)
            .inject(this)

        val vm = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]

        launch {
            repository.getPopularMovies(true)
                .onEach {
                    progressBar.visibility = if(it is Data.LOADING) View.VISIBLE else View.GONE
                }
                .filter { it !is Data.LOADING }
                .catch {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                }
                .extractData()
                .collect{

                }
        }
    }
}
