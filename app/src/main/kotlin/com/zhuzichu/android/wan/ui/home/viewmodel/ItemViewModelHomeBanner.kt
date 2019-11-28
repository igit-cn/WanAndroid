package com.zhuzichu.android.wan.ui.home.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.uber.autodispose.android.autoDispose
import com.zhuzichu.android.libs.internal.MainHandler
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.bindToSchedulers
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.shared.widget.banner.BannerHelper
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass


class ItemViewModelHomeBanner(
    viewModel: ViewModelAnalyticsBase
) : ItemViewModelAnalyticsBase(viewModel) {

    val items = ObservableArrayList<Any>()

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelBanner>(BR.item, R.layout.item_banner)
    }

    private val bannerHelper = BannerHelper(
        viewModel,
        items
    )

    val recyclerCommand = bannerHelper.recyclerCommand

    fun update(list: List<Any>) = bannerHelper.update(list)
}