package com.ssa.aholdtest.base

import com.ssa.aholdtest.base.Navigation.Screen
import com.ssa.aholdtest.extension.scopedScreen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.navigationGraph() {
    scopedScreen(Screen.ListItems.route, Screen.ListItems.content)
}

