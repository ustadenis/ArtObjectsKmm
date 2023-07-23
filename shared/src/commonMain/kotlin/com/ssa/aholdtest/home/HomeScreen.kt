package com.ssa.aholdtest.home

import androidx.compose.runtime.Composable
import com.ssa.aholdtest.extension.CustomNavigationContent
import ru.alexgladkov.odyssey.compose.RootController
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration

@Composable
fun HomeScreen(
    configuration: OdysseyConfiguration,
    router: RootController
) {
    CustomNavigationContent(
        configuration,
        router
    )
}