package com.dzenm.composeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dzenm.composeui.ui.theme.ComposeUITheme
import com.dzenm.composeui.ui.theme.Purple500
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldExample()
                }
            }
        }
    }

    @ExperimentalPagerApi
    @Preview
    @Composable
    fun ScaffoldExample() {
        val scaffoldState = rememberScaffoldState(
            rememberDrawerState(initialValue = DrawerValue.Closed)
        )
        val coroutineScope = rememberCoroutineScope()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBarView(onMenuClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                })
            },
            bottomBar = { BottomAppBarView() },
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
        ) {
            ContentView(paddingValues = it)
        }
    }

    @ExperimentalPagerApi
    @Composable
    fun ContentView(paddingValues: PaddingValues) {
        val model: MainModel = viewModel()
        val selectedIndex by model.selectedIndex.observeAsState(0)
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.White)
                .padding(paddingValues)
        ) {
            val pagerState = rememberPagerState(initialPage = selectedIndex)
            HorizontalPager(count = 4, state = pagerState) { page ->
                Card(
                    Modifier
                        .graphicsLayer {
                            // Calculate the absolute offset for the current page from the
                            // scroll position. We use the absolute value which allows us to mirror
                            // any effects for both directions
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        }
                        .fillMaxWidth()
                        .aspectRatio(1f)
                ) {
                    Text(text = "Content")
                }
            }
        }
    }

    @Composable
    fun Drawer() {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            repeat(5) { item ->
                Text(text = "Item $item", modifier = Modifier.padding(8.dp), color = Color.White)
            }
        }
    }

    // AppBar
    @Composable
    fun TopAppBarView(onMenuClicked: () -> Unit) {
        TopAppBar(
            title = {
                Text(text = "Scaffold Title", color = Color.Red)
            },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    modifier = Modifier.clickable(onClick = onMenuClicked),
                    tint = Color.White,
                )
            },
            backgroundColor = Purple500,
            elevation = 12.dp
        )
    }

    @Composable
    fun BottomAppBarView() {
        BottomAppBar(backgroundColor = Color.White) {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                BottomAppBarItem(title = "主页", index = 0)
                BottomAppBarItem(title = "圈子", index = 1)
                BottomAppBarItem(title = "导航", index = 2)
                BottomAppBarItem(title = "我的", index = 3)
            }
        }
    }

    @Composable
    fun RowScope.BottomAppBarItem(title: String, index: Int) {
        val model: MainModel = viewModel()
        val selectedIndex by model.selectedIndex.observeAsState(0)
        val color = if (selectedIndex == index) {
            Color.Blue
        } else {
            Color.Black
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1.0f)
                .fillMaxHeight()
                .clickable {
                    model.updateSelectedIndex(index = index)
                },
        ) {
            Text(text = title, color = color)
        }
    }
}

