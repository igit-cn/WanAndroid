package com.zhuzichu.android.wan.ui.demo.opencv.main.fragment

import com.zhuzichu.android.wan.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentGrayBinding
import com.zhuzichu.android.wan.ui.demo.opencv.main.viewmodel.ViewModelBlur

class FragmentBlur : FragmentAnalyticsBase<FragmentGrayBinding, ViewModelBlur>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_blur

}