package com.niraj.moviesapp.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.niraj.moviesapp.ui.navigations.BottomNavBar
import com.niraj.moviesapp.ui.navigations.NavGraph


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MoviesApp(modifier: Modifier = Modifier) {

    val navController = rememberAnimatedNavController()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        bottomBar = { BottomNavBar(navController) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            NavGraph(navController)
        }
    }

}