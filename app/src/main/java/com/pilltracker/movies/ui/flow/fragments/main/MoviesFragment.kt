package com.pilltracker.movies.ui.flow.fragments.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pilltracker.movies.databinding.FragmentMoviesBinding
import com.pilltracker.movies.model.Result
import com.pilltracker.movies.ui.base.BaseFragment
import com.pilltracker.movies.ui.flow.activity.MainViewModel
import com.pilltracker.movies.ui.flow.fragments.main.adapter.MoviesAdapter
import javax.inject.Inject


class MoviesFragment : BaseFragment<MoviesViewModel, FragmentMoviesBinding>(
    FragmentMoviesBinding::inflate
) {

    private val sharedViewModel: MainViewModel by activityViewModels()

    @Inject
    lateinit var moviesAdapter: MoviesAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()

    }


    override fun initData() {
        viewModel.getPopularMovies()

    }


    override fun initListeners() {

        with(ui) {
            buttonFilterDisplaying.setOnClickListener {
                viewModel.getCurrentDisplaying()
            }

            buttonFilterPopular.setOnClickListener {
                viewModel.getPopularMovies()
            }


            buttonFilterFavorites.setOnClickListener {
                moviesAdapter.updateMovies(sharedViewModel.favorites)
            }
        }

    }


    override val viewModelClass: Class<MoviesViewModel>
        get() = MoviesViewModel::class.java

    override fun observeViewModel() {

        viewModel.getMovies.observe(this){
            moviesAdapter.updateMovies(it)
        }
    }

    private fun initViews() {
        with(ui.recyclerView) {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = moviesAdapter
            setHasFixedSize(false)
            moviesAdapter.onItemClick = { onItemClickListener(it) }
        }

    }

    private fun onItemClickListener(movie: Result) {
        val direction = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(movie)
        findNavController().navigate(direction)
    }

}


