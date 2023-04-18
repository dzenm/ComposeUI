package com.dzenm.composeui.nav

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dzenm.composeui.screen.login.LoginScreen
import com.dzenm.composeui.screen.main.MainScreen
import com.dzenm.composeui.screen.main.home.HomeScreen
import com.dzenm.composeui.screen.main.me.MeScreen
import com.dzenm.composeui.screen.main.nav.NavScreen
import com.google.accompanist.pager.ExperimentalPagerApi

object NavManager {

    @SuppressLint("StaticFieldLeak")
    lateinit var navController: NavHostController

    @ExperimentalPagerApi
    @Composable
    fun BuildNavigation() {
        navController = rememberNavController()
        //导航
        NavHost(navController = navController, startDestination = Routers.Main, builder = {
            composable(Routers.Login) {
                LoginScreen()
            }
            composable(Routers.Main) {
                MainScreen()
            }
            composable(Routers.Home) {
                HomeScreen()
            }
            composable(Routers.Nav) {
                NavScreen()
            }
            composable(Routers.Me) {
                MeScreen()
            }
        })
    }
}