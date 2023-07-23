package com.ssa.aholdtest.extension

import androidx.compose.runtime.Composable
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.ssa.aholdtest.navigation.NavViewModelStore
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.ParametersDefinition
import ru.alexgladkov.odyssey.compose.RenderWithParams
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.helpers.FlowBuilder
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

actual fun RootComposeBuilder.scopedScreen(
    name: String,
    content: RenderWithParams<Any?>
) {
    screen(name) { params ->
        val vmStoreProvider = getViewModel<NavViewModelStore>()
        LocalRootController.current.onScreenRemove = {
            vmStoreProvider.clear(name)
        }
        content(params)
    }
}

actual fun FlowBuilder.scopedScreen(
    name: String,
    content: RenderWithParams<Any?>
) {
    screen(name) { params ->
        val vmStoreProvider = getViewModel<NavViewModelStore>()
        LocalRootController.current.onScreenRemove = {
            vmStoreProvider.clear(name)
        }
        content(params)
    }
}

@Composable
actual inline fun <reified T : KMMViewModel> koinViewModel(
    noinline parameters: ParametersDefinition?
): T {
    val vmStoreProvider = getViewModel<NavViewModelStore>()
    val name = LocalRootController.current.currentScreen.value?.screen?.realKey ?: "root"
    return getViewModel(
        viewModelStoreOwner = vmStoreProvider.getStoreOwner(name),
        parameters = parameters
    )
}