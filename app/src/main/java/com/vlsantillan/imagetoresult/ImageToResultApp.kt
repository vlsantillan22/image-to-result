package com.vlsantillan.imagetoresult

import android.app.Application
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class of the project
 *
 * Created by Vincent Santillan on 23/06/2022.
 */
@HiltAndroidApp
class ImageToResultApp : Application(), CameraXConfig.Provider {
    override fun getCameraXConfig(): CameraXConfig {
        return Camera2Config.defaultConfig()
    }
}