package com.dzenm.composeui.screen.main.me

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.dzenm.composeui.R
import com.dzenm.composeui.ui.theme.Purple500

/**定义配置界面*/
@Preview
@Composable
fun MeScreen() {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        //定义头部
        topBar = {
            TopAppBarView()
        },
        //定义中心区内容，实现界面的切换
        content = {
            ContentView(paddingValues = it)
        },
    )
}

@Composable
private fun TopAppBarView() {
    TopAppBar(
        title = {
            Text(text = "Me Title", color = Color.White)
        },
        backgroundColor = Purple500,
    )
}

@Composable
private fun ContentView(paddingValues: PaddingValues) {
    Column(
        content = {
            Text(
                "配置界面",
                fontSize = 36.sp,
                color = Color.White
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.teal_200)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    )
}