package com.vlsantillan.imagetoresult.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.vlsantillan.imagetoresult.ui.camera.CameraScreen
import com.vlsantillan.imagetoresult.ui.camera.CameraViewModel
import com.vlsantillan.imagetoresult.ui.home.HomeScreen

/**
 * The Navigation graph of the app
 *
 * Created by Vincent Santillan on 23/06/2022.
 */

@ExperimentalPermissionsApi
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

        composable(ImageToResultDestinations.HOME_ROUTE) { backStackEntry ->
            val homeBackStackEntry = remember(backStackEntry) {
                navController.getBackStackEntry(ImageToResultDestinations.HOME_ROUTE)
            }
            val viewModel = hiltViewModel<CameraViewModel>(homeBackStackEntry)
            HomeScreen(viewModel) {
                navigator.navigateToCamera()
            }
        }

        composable(ImageToResultDestinations.CAMERA_ROUTE) { backStackEntry ->
            val homeBackStackEntry = remember(backStackEntry) {
                navController.getBackStackEntry(ImageToResultDestinations.HOME_ROUTE) // To use same instance of CameraViewModel in this Navgraph
            }
            val viewModel = hiltViewModel<CameraViewModel>(homeBackStackEntry)
            CameraScreen(viewModel) {
                navController.popBackStack()
            }
        }
    }
}