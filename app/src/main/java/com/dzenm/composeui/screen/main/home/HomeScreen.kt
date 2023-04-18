package com.dzenm.composeui.screen.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dzenm.composeui.R
import com.dzenm.composeui.nav.NavManager
import com.dzenm.composeui.nav.Routers


@Composable
fun HomeScreen() {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.teal_200)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            Text(
                text = "首页界面", color = Color.White,
                fontSize = 36.sp, fontWeight = FontWeight.ExtraBold
            )
            Button(onClick = {
                NavManager.navController.navigate(Routers.Login)
            }) {
                Text(
                    text = "注销", color = Color.White, fontWeight = FontWeight.ExtraBold
                )
            }
        })
}