package com.zhuzichu.android.wan.di

import com.zhuzichu.android.mvvm.di.ActivityScoped
import com.zhuzichu.android.wan.ActivityMain
import com.zhuzichu.android.wan.ui.account.ActivityAccount
import com.zhuzichu.android.wan.ui.account.login.module.ModuleLogin
import com.zhuzichu.android.wan.ui.home.module.ModuleHome
import com.zhuzichu.android.wan.ui.home.module.ModuleHomeArticle
import com.zhuzichu.android.wan.ui.home.module.ModuleHomeProject
import com.zhuzichu.android.wan.ui.main.module.ModuleMain
import com.zhuzichu.android.wan.ui.me.module.ModuleMe
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            //fragments
            ModuleMain::class,
            ModuleHome::class,
            ModuleMe::class,
            ModuleHomeArticle::class,
            ModuleHomeProject::class
        ]
    )
    internal abstract fun mainActivity(): ActivityMain

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            //fragments
            ModuleLogin::class
        ]
    )
    internal abstract fun accountActivity(): ActivityAccount
}