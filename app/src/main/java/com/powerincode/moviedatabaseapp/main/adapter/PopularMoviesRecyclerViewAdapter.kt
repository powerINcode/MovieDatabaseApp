package com.powerincode.moviedatabaseapp.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.powerincode.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.powerincode.middleware.movies.Movie
import com.powerincode.moviedatabaseapp.R
import kotlinx.android.synthetic.main.item_popular_movie.view.*

class PopularMoviesRecyclerViewAdapter : BaseRecyclerViewAdapter<Movie>() {
    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_popular_movie, parent, false))
    }

    private inner class ViewHolder(view: View) : BaseViewHolder(view) {
        private val title = itemView.movieTitle
        override fun onBind(data: Movie) {
            title.text = data.title
        }
    }
}