package com.ssa.aholdtest.extension

import androidx.compose.runtime.Composable
import com.rickclephas.kmm.viewmodel.KMMViewModel
import org.koin.core.parameter.ParametersDefinition
import ru.alexgladkov.odyssey.compose.RenderWithParams
import ru.alexgladkov.odyssey.compose.helpers.FlowBuilder
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

expect fun RootComposeBuilder.scopedScreen(
    name: String,
    content: RenderWithParams<Any?>
)

expect fun FlowBuilder.scopedScreen(
    name: String,
    content: RenderWithParams<Any?>
)

@Composable
expect inline fun <reified T : KMMViewModel> koinViewModel(
    noinline parameters: ParametersDefinition? = null,
): T