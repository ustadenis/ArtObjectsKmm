package com.ssa.aholdtest.artobjects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ssa.aholdtest.Res
import com.ssa.aholdtest.common.onSurfaceLight
import com.ssa.aholdtest.extension.koinViewModel
import com.ssa.aholdtest.widget.AppBar
import com.ssa.aholdtest.widget.ImageWidget
import com.ssa.aholdtest.widget.InfoBanner
import com.ssa.domain.model.ArtObjectsItem
import dev.icerock.moko.resources.compose.stringResource

@Composable
expect fun PaginatedList(
    modifier: Modifier,
    viewModel: IItemsViewModel,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    content: @Composable (item: ArtObjectItemType) -> Unit
)

@Composable
fun ItemsScreen() {
    val viewModel: IItemsViewModel = koinViewModel<ItemsViewModel>()
    val screenState = viewModel.screenState.collectAsState()

    Scaffold(
        topBar = {
            AppBar(
                modifier = Modifier,
                title = stringResource(Res.strings.items_screen_title),
                showBackButton = false
            )
        }
    ) {
        ArtObjectsList(viewModel)

        when (screenState.value) {
            is ScreenState.Loading -> LoadingProgress()
            is ScreenState.Empty -> EmptyListWarning()
            else -> Unit
        }
    }
}

@Composable
private fun ArtObjectsList(
    viewModel: IItemsViewModel
) {
    PaginatedList(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        viewModel = viewModel
    ) {
        when (it) {
            is ArtObjectItemType.ArtObject -> CategoryItem(it.item)
            is ArtObjectItemType.Category -> CategoryTitleItem(it.name)
        }
    }
}

@Composable
private fun EmptyListWarning() {
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 24.dp)
    ) {
        EmptyCategoriesBanner()
    }
}

@Composable
private fun LoadingProgress() {
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 24.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp).align(Alignment.Center)
        )
    }
}

@Composable
private fun CategoryItem(
    item: ArtObjectsItem,
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colors.surface),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ) {
            ImageWidget(
                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(16.dp)),
                placeholder = Icons.Filled.Star,
                url = item.webImage?.url
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    item.title,
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    item.longTitle,
                    style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSurfaceLight)
                )
            }
        }
    }
}

@Composable
private fun EmptyCategoriesBanner() {
    InfoBanner(
        modifier = Modifier.fillMaxWidth(),
        title = stringResource(Res.strings.items_screen_banner_title),
        content = stringResource(Res.strings.items_screen_banner_content),
        icon = {
            Icon(
                Icons.Filled.Add,
                modifier = Modifier.size(32.dp),
                contentDescription = null,
                tint = MaterialTheme.colors.secondary
            )
        },
    )
}

@Composable
private fun CategoryTitleItem(
    title: String
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            title,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
        )
    }
}