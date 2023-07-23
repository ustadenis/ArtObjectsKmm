package com.ssa.aholdtest

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.window.ComposeUIViewController
import com.ssa.aholdtest.base.navigationGraph
import com.ssa.aholdtest.common.MyApplicationTheme
import com.ssa.aholdtest.extension.createRootController
import com.ssa.aholdtest.home.HomeScreen
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration

fun HomeViewController() = ComposeUIViewController {
    MyApplicationTheme {
        Column {
            val configuration = OdysseyConfiguration()
            val router = createRootController {
                navigationGraph()
            }

            HomeScreen(configuration = configuration, router = router)
        }
    }
}