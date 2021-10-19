package com.pilltracker.movies.ui.flow.fragments.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pilltracker.movies.R
import com.pilltracker.movies.data.network.Resource
import com.pilltracker.movies.databinding.FragmentMoviesBinding
import com.pilltracker.movies.model.Result
import com.pilltracker.movies.ui.base.BaseFragment
import com.pilltracker.movies.ui.flow.activity.MainViewModel
import com.pilltracker.movies.ui.flow.fragments.main.adapter.MoviesAdapter
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject


class MoviesFragment : BaseFragment<MoviesViewModel, FragmentMoviesBinding>(
    FragmentMoviesBinding::inflate
) {

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
                viewModel.getFavoritesMovies()
            }
        }

    }


    override val viewModelClass: Class<MoviesViewModel>
        get() = MoviesViewModel::class.java

    override fun observeViewModel() {

        viewModel.getMovies.observe(this){ response ->
            when(response.status){
                Resource.Status.SUCCESS ->  moviesAdapter.updateMovies(response.data!!)
                Resource.Status.ERROR -> showErrorDialog(response.throwable)
                Resource.Status.LOADING -> showLoading(true)
                Resource.Status.HIDE_LOADING -> showLoading(false)
            }
        }
    }

    private fun showErrorDialog(throwable: Throwable?) {

        var message = getString(R.string.general_error)
        if(throwable is SocketTimeoutException) {
            message = getString(R.string.error_timeout)
        } else if(throwable is HttpException && throwable.code() == 404){
            message = getString(R.string.error_404)
        }

        val materialAlertDialog = MaterialAlertDialogBuilder(requireActivity())
        materialAlertDialog.setMessage(message)
        materialAlertDialog.setPositiveButton(getString(R.string.ok)) { d, _ ->
            d.dismiss()
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


