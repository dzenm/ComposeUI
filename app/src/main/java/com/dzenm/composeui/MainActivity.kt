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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dzenm.composeui.ui.theme.ComposeUITheme
import com.dzenm.composeui.ui.theme.Purple700
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
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
            bottomBar = {
                BottomAppBarView()
            },
            drawerContent = {
                ContentBodyView()
            },
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
            Padding(it)
        }
    }

    @Composable
    fun Padding(paddingValues: PaddingValues) {
        Modifier.padding()
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
            backgroundColor = Purple700,
            elevation = 12.dp
        )
    }

    @Composable
    fun ContentBodyView() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Text("Content Text", color = Purple700)
        }
    }

    @Composable
    fun BottomAppBarView() {
        BottomAppBar(backgroundColor = Purple700) {
            Text(text = "Bottom Bar", color = Color.White)
        }
    }
}

