package com.ssa.aholdtest.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ssa.aholdtest.base.navigationGraph
import com.ssa.aholdtest.common.MyApplicationTheme
import com.ssa.aholdtest.extension.createRootController
import com.ssa.aholdtest.home.HomeScreen
import ru.alexgladkov.odyssey.compose.extensions.setupWithActivity
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val systemUIController = rememberSystemUiController()

                val configuration = OdysseyConfiguration(
                    canvas = this,
                    backgroundColor = MaterialTheme.colors.background
                )

                systemUIController.setSystemBarsColor(
                    color = MaterialTheme.colors.surface
                )
                systemUIController.setStatusBarColor(
                    color = MaterialTheme.colors.background
                )

                val router = createRootController {
                    navigationGraph()
                }.also { it.setupWithActivity(this) }

                HomeScreen(configuration = configuration, router = router)
            }
        }
    }
}
