package com.zhuzichu.android.wan.ui.opencv.main.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelOpencv(
    viewModel: BaseViewModel,
    type: Int,
    @StringRes stringId: Int,
    closure: Int.() -> Unit
) : ItemViewModelAnalyticsBase(viewModel) {

    val title = MutableLiveData(stringId)

    val onClickItem = BindingCommand<Any>({
        closure.invoke(type)
    })
}