package com.pilltracker.movies.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.pilltracker.movies.Injectable
import com.pilltracker.movies.databinding.ActivityBaseBinding
import com.pilltracker.movies.extentions.visible
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity<VM : ViewModel, VB : ViewBinding>
    : AppCompatActivity(), HasAndroidInjector, Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    private lateinit var baseBinding: ActivityBaseBinding
    lateinit var viewModel: VM
    lateinit var contentBinding : VB


    abstract fun getViewBinding(): VB

    abstract val viewModelClass: Class<VM>

    abstract fun observeViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        setViewModel()
        observeViewModel()
    }

    private fun setContentView() {
        baseBinding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)
        contentBinding = getViewBinding()
        baseBinding.contentContainer.addView(contentBinding.root)

    }


    private fun setViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass)

    }


    fun showLoading(show: Boolean){
        if(show) {
            baseBinding.loadingView.bringToFront()
        }
        baseBinding.loadingView.visible(show, View.GONE)
    }


}