package com.ssa.aholdtest.artobjects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items as pagingItems

@Composable
actual fun PaginatedList(
    modifier: Modifier,
    viewModel: IItemsViewModel,
    contentPadding: PaddingValues,
    reverseLayout: Boolean,
    verticalArrangement: Arrangement.Vertical,
    content: @Composable (item: ArtObjectItemType) -> Unit
) {
    val lazyItems = viewModel.artObjects.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        verticalArrangement = verticalArrangement
    ) {
        pagingItems(lazyItems) {
            if (it != null) {
                content(it)
            }
        }
        if (lazyItems.loadState.append is LoadState.Loading) {
            item {
                ProgressBar()
            }
        }
    }
}

@Composable
private fun ProgressBar() {
    Box(
        modifier = Modifier.fillMaxWidth().height(200.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp).align(Alignment.Center)
        )
    }
}