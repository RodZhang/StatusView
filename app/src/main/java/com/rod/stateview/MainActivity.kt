package com.rod.stateview

import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getContentLayoutId() = R.layout.activity_main

    override fun getContentContainer(): ViewGroup = container

    override fun getContentView(): View = content

    override fun setupView() {
    }
}
