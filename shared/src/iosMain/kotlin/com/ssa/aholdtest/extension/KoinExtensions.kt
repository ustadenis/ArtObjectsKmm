package com.ssa.aholdtest.extension

import androidx.compose.runtime.Composable
import com.rickclephas.kmm.viewmodel.KMMViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.ParametersDefinition
import ru.alexgladkov.odyssey.compose.RenderWithParams
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.helpers.FlowBuilder
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

actual fun RootComposeBuilder.scopedScreen(
    name: String,
    content: RenderWithParams<Any?>
) {
    screen(name) { params ->
        content(params)
    }
}

actual fun FlowBuilder.scopedScreen(
    name: String,
    content: RenderWithParams<Any?>
) {
    screen(name) { params ->
        content(params)
    }
}

@Composable
actual inline fun <reified T : KMMViewModel> koinViewModel(
    noinline parameters: ParametersDefinition?
): T {
    return object : KoinComponent {}.get(
        parameters = parameters
    )
}