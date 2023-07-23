package com.ssa.aholdtest.extension

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.alexgladkov.odyssey.compose.RootController
import ru.alexgladkov.odyssey.compose.base.Navigator
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalNavigator
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.configuration.DefaultModalConfiguration
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.StartScreen
import ru.alexgladkov.odyssey.core.configuration.DisplayType

fun createRootController(navigationGraph: RootComposeBuilder.() -> Unit): RootController {
    return RootComposeBuilder().apply(navigationGraph).build()
}

@Composable
internal fun CustomNavigationContent(
    configuration: OdysseyConfiguration,
    rootController: RootController,
    topPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp
) {
    rootController.backgroundColor = configuration.backgroundColor

    CompositionLocalProvider(
        LocalRootController provides rootController
    ) {
        Box(
            modifier = Modifier.padding(top = topPadding, bottom = bottomPadding)
        ) {
            ModalNavigator(
                configuration = DefaultModalConfiguration(
                    configuration.backgroundColor,
                    DisplayType.EdgeToEdge
                )
            ) {
                when (val startScreen = configuration.startScreen) {
                    is StartScreen.Custom -> Navigator(startScreen = startScreen.startName)
                    StartScreen.First -> Navigator(startScreen = rootController.getFirstScreenName())
                }
            }
        }
    }
}