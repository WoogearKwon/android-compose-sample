package net.huray.basiccodelab

import android.app.Application
import net.huray.basiccodelab.data.AppContainer
import net.huray.basiccodelab.data.AppContainerImpl

class NesApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}