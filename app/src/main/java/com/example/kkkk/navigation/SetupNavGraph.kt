package com.example.kkkk.navigation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.kkkk.accueil.PageAccueil
import com.example.kkkk.date.CeDate
import com.example.kkkk.date.ListeDate
import com.example.kkkk.poesie.CePoeme
import com.example.kkkk.poesie.JusteUnPoeme
import com.example.kkkk.poesie.TesPoemes
import com.example.kkkk.splash.SplashScreenSun
import com.example.kkkk.stat.SuiviActivites


@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(
    modifier: Modifier,
    navHostController: NavHostController,context : Context
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.SplachScreenSun.route
    ) {
        composable(
            route = Screen.SplachScreenSun.route
        ) {
            SplashScreenSun(
                navHostController = navHostController
            )
        }
        composable(
            route = Screen.PageAccueil.route
        ) {
            PageAccueil(navHostController,context)
        }
        composable(
            route = Screen.CeDate.route,
            arguments = listOf(
                navArgument("phraseId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val phraseId = backStackEntry.arguments?.getInt("phraseId") ?: -1
            CeDate(phraseId = phraseId,navHostController)
        }
        composable(
            route = Screen.JusteUnPoeme.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            JusteUnPoeme(navHostController,id = id)
        }
        composable(
            route = Screen.ListeDate.route
        ) {
            ListeDate(navHostController)
        }
        composable(
            route = Screen.SuiviActivites.route
        ) {
            SuiviActivites(navHostController)
        }
        composable(route = Screen.CePoeme.route) {
            CePoeme(navHostController)
        }
        composable(route = Screen.TesPoemes.route) {
            TesPoemes(navHostController)
        }


    }
}

sealed class Screen(val route: String) {
    object SplachScreenSun : Screen("SplashScreenSun")
    object PageAccueil : Screen("PageAccueil")
    object CeDate : Screen("cedate/{phraseId}") {
        fun createRoute(phraseId: Int) = "cedate/$phraseId"
    }
    object ListeDate : Screen("ListeDate")
    object SuiviActivites : Screen("SuiviActivites")
    object TesPoemes : Screen("TesPoemes")
    object CePoeme : Screen("CePoeme")
    object JusteUnPoeme : Screen("JusteUnPoeme/{id}") {
        fun createRoutePoeme(id: Int) = "JusteUnPoeme/$id"
    }
}