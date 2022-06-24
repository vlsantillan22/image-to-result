package com.vlsantillan.imagetoresult

import androidx.navigation.NavHostController
import com.vlsantillan.imagetoresult.ImageToResultDestinations.CAMERA_ROUTE

/**
 * All navigation related objects
 *
 * Created by Vincent Santillan on 23/06/2022.
 */

object ImageToResultDestinations {
    const val HOME_ROUTE = "home"
    const val CAMERA_ROUTE = "camera"
}

class ImageToResultNavigator(private val navController: NavHostController) {

    fun navigateToCamera() {
        navController.navigate(CAMERA_ROUTE)
    }
}