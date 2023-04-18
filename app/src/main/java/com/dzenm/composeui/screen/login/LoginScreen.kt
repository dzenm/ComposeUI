package com.dzenm.composeui.screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dzenm.composeui.nav.NavManager
import com.dzenm.composeui.nav.Routers
import com.dzenm.composeui.ui.theme.Purple500


/**
 * 登录页面
 */
@Composable
fun LoginScreen() {
    Scaffold(
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
            Text(text = "Login", color = Color.White)
        },
        backgroundColor = Purple500,
    )
}

@Composable
private fun ContentView(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Box(modifier = Modifier.height(200.dp))
            TextField(
                value = "",
                onValueChange = {

                },
            )
            Box(modifier = Modifier.height(24.dp))
            TextField(
                value = "",
                onValueChange = {

                },
            )
            Box(modifier = Modifier.height(24.dp))
            Button(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth(),
                onClick = {
                    NavManager.navController.navigate(Routers.Main)
                },
            ) {
                Text(text = "登录")
            }
        },
    )
}