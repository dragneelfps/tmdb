package com.nooblabs.tmdb.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.nooblabs.tmdb.MyApp
import com.nooblabs.tmdb.R
import com.nooblabs.tmdb.getViewModel
import com.nooblabs.tmdb.logd
import com.nooblabs.tmdb.viewmodel.ListMoviesViewModel
import com.nooblabs.tmdb.viewmodel.ViewModelFactory
import javax.inject.Inject

open class ListMoviesActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory<ListMoviesViewModel>

    private lateinit var viewModel: ListMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movies)

        (application as MyApp).getAppComponent().inject(this)

        viewModel = getViewModel(viewModelFactory)
         viewModel.getConfiguration().observe(this, Observer { configuration ->
            logd(configuration)
        })
    }
}
