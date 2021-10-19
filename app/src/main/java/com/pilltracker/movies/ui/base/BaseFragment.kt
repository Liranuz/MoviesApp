package com.pilltracker.movies.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.pilltracker.movies.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VM : ViewModel, VB : ViewBinding>(
    private val inflate: Inflate<VB>
    ) : Fragment(), HasAndroidInjector, Injectable {

    protected lateinit var viewModel: VM
    private lateinit var baseActivity: BaseActivity<*,*>
    private var _binding: VB? = null
    val ui get() = _binding!!

    abstract val viewModelClass: Class<VM>

    abstract fun observeViewModel()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    abstract fun initListeners()

    abstract fun initData()

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            if(context is BaseActivity<*,*>) {
                baseActivity = activity as BaseActivity<*,*>
            }
        } catch (e: ClassCastException){
            e.printStackTrace()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate.invoke(inflater, container, false)
        return ui.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(viewModelClass)
        observeViewModel()
        initListeners()
        initData()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    open fun showLoading(show: Boolean) {
        activity?.let {
           baseActivity.showLoading(show)
        }
    }


}

