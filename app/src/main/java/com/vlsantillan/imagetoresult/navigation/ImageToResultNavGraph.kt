package com.vlsantillan.imagetoresult.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vlsantillan.imagetoresult.ImageToResultDestinations
import com.vlsantillan.imagetoresult.ImageToResultNavigator
import com.vlsantillan.imagetoresult.ui.camera.CameraScreen
import com.vlsantillan.imagetoresult.ui.home.HomeScreen

/**
 * The Navigation graph of the app
 *
 * Created by Vincent Santillan on 23/06/2022.
 */

@Composable
fun ImageToResultNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ImageToResultDestinations.HOME_ROUTE,
    navigator: ImageToResultNavigator = remember(navController) {
        ImageToResultNavigator(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(ImageToResultDestinations.HOME_ROUTE) {
            HomeScreen {
                navigator.navigateToCamera()
            }
        }

        composable(ImageToResultDestinations.CAMERA_ROUTE) {
            CameraScreen {
                navController.popBackStack()
            }
        }
    }
}