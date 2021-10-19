package com.pilltracker.movies.ui.flow.activity

import com.pilltracker.movies.databinding.ActivityMainBinding
import com.pilltracker.movies.ui.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override fun observeViewModel() {

    }
}