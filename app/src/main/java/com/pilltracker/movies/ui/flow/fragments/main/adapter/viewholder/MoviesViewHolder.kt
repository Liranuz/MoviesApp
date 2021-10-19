package com.pilltracker.movies.ui.flow.fragments.main.adapter.viewholder

import com.pilltracker.movies.constants.Const
import com.pilltracker.movies.databinding.MovieListItemBinding
import com.pilltracker.movies.model.Result
import com.pilltracker.movies.ui.base.BaseViewHolder
import com.squareup.picasso.Picasso


class MoviesViewHolder(
    private val binding: MovieListItemBinding,
    val onItemClick: ((item: Result) -> Unit)?,
) : BaseViewHolder<Result>(binding.root) {

    override fun bind(movie: Result?) {
        movie?.let {
            binding.title.text = it.title

            it.posterPath?.let { path ->
                if(path.isNotEmpty()){
                    Picasso.with(itemView.context)
                        .load(Const.MOVIEDB_SMALL_POSTER_URL + path)
                        .into(binding.movieThumbnail)
                }
            }
        }


        itemView.setOnClickListener {
            onItemClick?.invoke(movie!!)
        }
    }



}
