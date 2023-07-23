package com.ssa.aholdtest.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.PictureInPictureAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ssa.aholdtest.Res
import com.ssa.aholdtest.common.bannerBackground
import com.ssa.aholdtest.common.onSurfaceLight
import com.ssa.aholdtest.details.IArtObjectDetailsViewModel.*
import com.ssa.aholdtest.extension.koinViewModel
import com.ssa.aholdtest.widget.AppBar
import com.ssa.aholdtest.widget.ImageWidget
import com.ssa.aholdtest.widget.InfoBanner
import com.ssa.aholdtest.widget.LoadingProgress
import com.ssa.aholdtest.widget.Tag
import com.ssa.domain.model.ArtObject
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ArtObjectsDetailsScreen(
    objectId: String
) {
    val viewModel: IArtObjectDetailsViewModel = koinViewModel<ArtObjectsDetailsViewModel>()
    val screenState = viewModel.screenState.collectAsState()

    LaunchedEffect(true) {
        viewModel.sendEvent(Event.RetrieveDetails(objectId))
    }

    Scaffold(
        topBar = {
            AppBar(
                modifier = Modifier,
                title = stringResource(Res.strings.art_objects_details_title),
            )
        }
    ) {
        when (val currentState = screenState.value) {
            is ScreenState.Loading -> LoadingProgress()
            is ScreenState.Data -> TranslationCard(
                modifier = Modifier.fillMaxSize(),
                artObject = currentState.artObject
            )

            is ScreenState.Error -> ErrorBanner()
        }
    }
}

@Composable
private fun TranslationCard(
    modifier: Modifier,
    artObject: ArtObject
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colors.surface),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TranslationPicture(
                modifier = Modifier.fillMaxWidth().height(240.dp),
                url = artObject.webImage?.url,
                tag = artObject.principalOrFirstMaker
            )
            val title = artObject.title
            if (title != null) {
                Spacer(modifier = Modifier.size(24.dp))
                Text(
                    title,
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                val subTitle = artObject.subTitle
                if (subTitle != null) {
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        subTitle,
                        style = MaterialTheme.typography.body2.copy(
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colors.onSurfaceLight
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            val description = artObject.description
            if (description != null) {
                Spacer(modifier = Modifier.size(24.dp))
                Text(
                    description,
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            val presentingDate = artObject.dating?.presentingDate
            if (presentingDate != null) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    stringResource(Res.strings.art_objects_details_presenting_date, presentingDate),
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}

@Composable
private fun TranslationPicture(
    modifier: Modifier,
    url: String?,
    tag: String? = null
) {
    Box(
        modifier = modifier.background(MaterialTheme.colors.bannerBackground)
    ) {
        ImageWidget(
            modifier = Modifier.fillMaxSize(),
            url = url,
            placeholder = Icons.Filled.PictureInPictureAlt,
            tint = MaterialTheme.colors.secondary
        )
        if (tag != null) {
            Tag(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp),
                title = tag
            )
        }
    }
}

@Composable
private fun ErrorBanner() {
    InfoBanner(
        modifier = Modifier.fillMaxWidth().padding(
            vertical = 8.dp, horizontal = 24.dp
        ),
        title = stringResource(Res.strings.art_objects_details_error_title),
        content = stringResource(Res.strings.art_objects_details_error_content),
        icon = {
            Icon(
                Icons.Filled.Error,
                modifier = Modifier.size(32.dp),
                contentDescription = null,
                tint = MaterialTheme.colors.secondary
            )
        },
    )
}