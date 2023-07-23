package com.ssa.aholdtest.base

import androidx.compose.runtime.Composable
import com.ssa.aholdtest.artobjects.ItemsScreen
import ru.alexgladkov.odyssey.compose.RenderWithParams

sealed interface Navigation {
    val route: String

    sealed class Screen(
        override val route: String
    ) : Navigation {
        abstract val content: RenderWithParams<Any?>

        object ListItems : Screen("listItems") {
            override val content: RenderWithParams<Any?>
                get() = @Composable {
                    ItemsScreen()
                }
        }
    }
}

