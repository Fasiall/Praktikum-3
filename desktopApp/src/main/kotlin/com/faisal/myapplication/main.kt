package com.faisal.myapplication

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val windowState = rememberWindowState(
        width = 460.dp,
        height = 820.dp
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "My Profile App",
        state = windowState,
        resizable = true
    ) {
        App()
    }
}