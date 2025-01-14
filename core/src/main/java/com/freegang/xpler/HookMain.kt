package com.freegang.xpler

import android.app.Application
import com.freegang.douyin.DouYinMain
import com.freegang.xpler.loader.injectClassLoader
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage

class HookMain : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        // do`t write the same hook logic in two methods at the same time, and do not call each other in the same way.
    }

    fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam, application: Application) {
        when (lpparam.packageName) {
            HookPackages.douYinPackageName -> {
                if (DouYinMain.awemeHostApplication.isInstance(application)) {
                    injectClassLoader(application.classLoader)
                    DouYinMain(application)
                }
            }
        }
    }
}