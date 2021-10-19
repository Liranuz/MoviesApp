package com.pilltracker.movies.ui.flow.fragments.movie_details

import androidx.core.view.ViewCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.github.ivbaranov.mfb.MaterialFavoriteButton
import com.pilltracker.movies.data.network.NetworkConstants
import com.pilltracker.movies.databinding.FragmentMovieDetailsBinding
import com.pilltracker.movies.model.Result
import com.pilltracker.movies.ui.base.BaseFragment
import com.pilltracker.movies.ui.flow.activity.MainViewModel
import com.squareup.picasso.Picasso

class MovieDetailsFragment : BaseFragment<MoviesDetailsViewModel, FragmentMovieDetailsBinding >(FragmentMovieDetailsBinding::inflate) {

    private val sharedViewModel: MainViewModel by activityViewModels()

    private lateinit var movie : Result

    override val viewModelClass: Class<MoviesDetailsViewModel>
        get() = MoviesDetailsViewModel::class.java

    override fun observeViewModel() {

        viewModel.isFavorite.observe(viewLifecycleOwner){ isFavorite ->
            ui.favoriteButton.isFavorite = isFavorite
        }
    }

    override fun initListeners() {

        ui.favoriteButton.setOnFavoriteChangeListener {
                buttonView: MaterialFavoriteButton?, favorite: Boolean ->

            if (favorite) {
                viewModel.saveFavoriteMovie(movie)
            } else {
                viewModel.removeFavoritMovie(movie)
            }
        }
    }

    override fun initData() {
        ViewCompat.setTranslationZ(requireView(), 100f)

        arguments?.let {
            val args = MovieDetailsFragmentArgs.fromBundle(it)
            movie = args.movieDetails
            setMovieDetails(movie)
            viewModel.isMovieFavorite(movie)
        }?:run{
            findNavController().popBackStack()
        }
    }

    private fun setMovieDetails(movie: Result) {
        with(ui) {
            movie.apply {
                userRating.text = voteAverage.toString()
                description.text = overview
                year.text = releaseDate
                Picasso.with(requireActivity())
                    .load(NetworkConstants.MOVIEDB_LARGE_POSTER_URL + posterPath)
                    .into(mainImage)
            }
        }
    }
}