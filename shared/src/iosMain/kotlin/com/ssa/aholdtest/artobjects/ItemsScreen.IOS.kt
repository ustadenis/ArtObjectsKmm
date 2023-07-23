package com.ssa.aholdtest.artobjects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
actual fun PaginatedList(
    modifier: Modifier,
    viewModel: IItemsViewModel,
    contentPadding: PaddingValues,
    reverseLayout: Boolean,
    verticalArrangement: Arrangement.Vertical,
    content: @Composable (item: ArtObjectItemType) -> Unit
) {
    val lazyItems = viewModel.artObjects.collectAsState(emptyList())

    val state = rememberLazyListState()

    val isAtBottom by remember {
        derivedStateOf {
            val layoutInfo = state.layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo

            if (layoutInfo.totalItemsCount == 0) {
                false
            } else {
                val lastVisibleItem = visibleItemsInfo.last()
                val viewportHeight = layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset

                (lastVisibleItem.index + 1 == layoutInfo.totalItemsCount) &&
                        ((lastVisibleItem.offset + lastVisibleItem.size) <= viewportHeight)
            }
        }
    }

    // Simplified realisation of the pagination as far as we don't have
    // ios implementation right now
    LaunchedEffect(isAtBottom) {
        if (isAtBottom) {
            viewModel.sendEvent(Event.RetrieveArtObjects(lazyItems.value.size / ITEMS_PER_PAGE))
        }
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        state = state,
        verticalArrangement = verticalArrangement
    ) {
        items(lazyItems.value) {
            content(it)
        }
    }
}