package com.zhuzichu.android.shared.widget.banner

import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.uber.autodispose.android.autoDispose
import com.zhuzichu.android.libs.internal.MainHandler
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.bindToSchedulers
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

class BannerHelper(
    val viewModel: ViewModelAnalyticsBase,
    private val items: ObservableArrayList<Any>
) {


    var state = SCROLL_STATE_IDLE

    var position = 0

    private val pagerSnapHelper by lazy {
        PagerSnapHelper()
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            state = newState
            val manager = (recyclerView.layoutManager as LinearLayoutManager)
            if (state == SCROLL_STATE_IDLE) {
                val view = pagerSnapHelper.findSnapView(manager)
                if (view != null) {
                    position = manager.getPosition(view)
                    if (position == 0) {
                        position = items.size - 2
                        manager.scrollToPositionWithOffset(position, 0)
                    }
                    if (position == items.size - 1) {
                        position = 1
                        manager.scrollToPositionWithOffset(1, 0)
                    }
                }
            }
        }
    }

    val recyclerCommand = BindingCommand<RecyclerView>(consumer = {
        this?.let {
            pagerSnapHelper.attachToRecyclerView(it)
            it.addOnScrollListener(scrollListener)
            it.post {
                Flowable.interval(0, 2, TimeUnit.SECONDS)
                    .bindToSchedulers()
                    .autoDispose(it)
                    .subscribe { _ ->
                        if (state == SCROLL_STATE_IDLE) {
                            it.smoothScrollToPosition(position++)
                        }
                    }
            }
        }
    })

    fun update(list: List<Any>) {
        items.clear()
        items.addAll(list)
        MainHandler.postDelayed(Runnable {
            items.add(list.size, list[0])
            items.add(0, list[list.size - 1])
        }, 100)

    }
}