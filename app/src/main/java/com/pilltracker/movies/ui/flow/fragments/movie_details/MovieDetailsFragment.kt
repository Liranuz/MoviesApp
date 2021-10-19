package com.pilltracker.movies.ui.flow.fragments.movie_details

import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.github.ivbaranov.mfb.MaterialFavoriteButton
import com.pilltracker.movies.constants.Const
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

        sharedViewModel.getIsExit.observe(viewLifecycleOwner){ isExist->
            val toastMessage = if(isExist) "Movie is already saved as favorite" else "Movie added to favorite list"
            Toast.makeText(requireActivity(), toastMessage, Toast.LENGTH_SHORT).show()
        }
    }

    override fun initListeners() {

        ui.favoriteButton.setOnFavoriteChangeListener {
                buttonView: MaterialFavoriteButton?, favorite: Boolean ->
             
            if (favorite and !sharedViewModel.isFavorite(movie.id)) {
                    movie.isFavorite = true
                    sharedViewModel.onFavoriteIconClicked(movie)
            } else if(!favorite and sharedViewModel.isFavorite(movie.id)){
                movie.isFavorite = false
                sharedViewModel.onRemoveIconClicked(movie)
            }
        }
    }

    override fun initData() {
        ViewCompat.setTranslationZ(requireView(), 100f)

        arguments?.let {
            val args = MovieDetailsFragmentArgs.fromBundle(it)
            movie = args.movieDetails
            setMovieDetails(movie)
        }?:run{ findNavController().popBackStack()}
    }

    private fun setMovieDetails(movie: Result) {
        with(ui) {
            movie.apply {
                favoriteButton.isFavorite = sharedViewModel.isFavorite(movie.id)
                userRating.text = voteAverage.toString()
                description.text = overview
                year.text = releaseDate
                Picasso.with(requireActivity())
                    .load(Const.MOVIEDB_LARGE_POSTER_URL + posterPath)
                    .into(mainImage)
            }
        }
    }
}