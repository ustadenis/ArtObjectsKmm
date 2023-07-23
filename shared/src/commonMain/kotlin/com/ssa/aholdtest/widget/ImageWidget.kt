package com.ssa.aholdtest.widget

import androidx.compose.foundation.Image
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.aakira.napier.Napier
import io.kamel.core.Resource
import io.kamel.image.asyncPainterResource
import io.ktor.http.Url
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Composable
fun ImageWidget(
    modifier: Modifier,
    url: String?,
    contentDescription: String? = null,
    placeholder: ImageVector? = null,
    errorImage: ImageVector? = null,
    tint: Color = MaterialTheme.colors.secondary
) {
    if (url == null) {
        Placeholder(
            modifier = modifier,
            placeholder = placeholder,
            tint = tint
        )
        return
    }
    val resource = asyncPainterResource(Url(url)) {
        coroutineContext = Dispatchers.IO + CoroutineExceptionHandler { _, ex ->
            Napier.e { "Load image error: $ex" }
        }
    }
    when (resource) {
        is Resource.Loading -> {
            Placeholder(
                modifier = modifier,
                placeholder = placeholder,
                tint = tint
            )
        }

        is Resource.Success -> {
            Image(
                modifier = modifier,
                painter = resource.value,
                contentDescription = contentDescription
            )
        }

        is Resource.Failure -> {
            Napier.e { "Load image error: ${resource.exception}" }
            if (errorImage == null) return
            Icon(
                modifier = modifier,
                imageVector = errorImage,
                contentDescription = null,
                tint = tint
            )
        }
    }
}

@Composable
private fun Placeholder(
    modifier: Modifier,
    placeholder: ImageVector?,
    tint: Color
) {
    if (placeholder == null) return
    Icon(
        modifier = modifier,
        imageVector = placeholder,
        contentDescription = null,
        tint = tint
    )
}