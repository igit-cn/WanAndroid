package com.zhuzichu.android.wan.ui.opencv.main.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.bindToSchedulers
import com.zhuzichu.android.shared.global.AppGlobal.context
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.manager.OpencvManager
import io.reactivex.Flowable
import javax.inject.Inject

class ViewModelBlur @Inject constructor(
    private val opencvManager: OpencvManager
) : ViewModelAnalyticsBase() {

    private val src = BitmapFactory.decodeResource(context.resources, R.mipmap.guidao)

    var width = 17

    var height = 17

    val min = 3

    val bitmap = MutableLiveData<Bitmap>().apply {
        value = src
    }

    val onSeekWidthCommand = BindingCommand<Int>(consumer = {
        this?.let {
            if (it % 3 != 0)
                return@BindingCommand
            width = it + min
            updateBitmap()
        }
    })

    val onSeekHeightCommand = BindingCommand<Int>(consumer = {
        this?.let {
            if (it % 3 != 0)
                return@BindingCommand
            height = it + min
            updateBitmap()
        }
    })

    val onClickReturn = BindingCommand<Any>({
        bitmap.value = src
    })

    private fun updateBitmap() {
        Flowable.just(src.copy(src.config, true))
            .map {
                opencvManager.blur(it, width, height)
            }
            .bindToSchedulers()
            .autoDispose(this)
            .subscribe {
                bitmap.value = it
            }
    }
}