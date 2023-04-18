package com.dzenm.composeui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dzenm.composeui.screen.main.home.HomeScreen
import com.dzenm.composeui.screen.main.me.MeScreen
import com.dzenm.composeui.screen.main.nav.NavScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/**
 * 主页面，包含四个子页面，可以点击切换
 */
@ExperimentalPagerApi
@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val pageState = rememberPagerState(initialPage = 0)

    Scaffold(
        scaffoldState = scaffoldState,
        //定义中心区内容，实现界面的切换
        content = {
            ContentView(paddingValues = it, pageState = pageState)
        },
        //定义底部的导航内容
        bottomBar = {
            BottomAppBarView(pageState = pageState)
        },
        //定义悬浮按钮
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    when (scaffoldState.snackbarHostState.showSnackbar(
                        message = "Snack Bar",
                        actionLabel = "Dismiss"
                    )) {
                        SnackbarResult.Dismissed -> {

                        }
                        SnackbarResult.ActionPerformed -> {

                        }
                    }
                }
            }) {
                Text(text = "+", color = Color.White)
            }
        }
    )
}

@ExperimentalPagerApi
@Composable
private fun ContentView(paddingValues: PaddingValues, pageState: PagerState) {
    val model: MainModel = viewModel()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(paddingValues)
    ) {
        HorizontalPager(
            count = 3,
            state = pageState,
            modifier = Modifier.weight(1.0f)
        ) { page ->
            model.updateSelectedIndex(currentPage)
            when (page) {
                0 -> HomeScreen()
                1 -> NavScreen()
                2 -> MeScreen()
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun BottomAppBarView(pageState: PagerState) {
    val scope = rememberCoroutineScope()
    val titles = listOf("主页", "导航", "我的")
    val images = listOf(Icons.Filled.Home, Icons.Filled.Navigation, Icons.Filled.Person)
    BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            repeat(titles.size) { i ->
                BottomAppBarItemView(title = titles[i], index = i, image = images[i]) {
                    scope.launch {
                        pageState.animateScrollToPage(i)
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.BottomAppBarItemView(title: String, index: Int, image: ImageVector, onClick: () -> Unit) {
    val model: MainModel = viewModel()
    val selectedIndex by model.selectedIndex.observeAsState(0)
    val color = if (selectedIndex == index) {
        MaterialTheme.colors.primary
    } else {
        Color.Gray
    }
    BottomNavigationItem(
        label = {
            Text(text = title, color = color)
        },
        selected = index == selectedIndex,
        onClick = {
            onClick()
        },
        icon = {
            Icon(
                imageVector = image,
                contentDescription = null,
                tint = color
            )
        },
    )
}
