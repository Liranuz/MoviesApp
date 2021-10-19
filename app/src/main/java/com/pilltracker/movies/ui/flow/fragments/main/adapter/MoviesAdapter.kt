package com.pilltracker.movies.ui.flow.fragments.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pilltracker.movies.databinding.MovieListItemBinding
import com.pilltracker.movies.model.Result
import com.pilltracker.movies.ui.base.BaseViewHolder
import com.pilltracker.movies.ui.flow.fragments.main.adapter.viewholder.MoviesViewHolder
import javax.inject.Inject

class MoviesAdapter @Inject constructor(
    private var movies: MutableList<Result>
    ) :RecyclerView.Adapter<BaseViewHolder<Result>>() {


    var onItemClick: ((item: Result) -> Unit)? = null


    fun updateMovies(movieList: MutableList<Result>) {
        movies.clear()
        movies.addAll(movieList)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : BaseViewHolder<Result> {
        val binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding, onItemClick)
    }


    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Result>, position: Int) {
        val currentMovie : Result = movies.get(position)
        holder.bind(currentMovie)
    }

}