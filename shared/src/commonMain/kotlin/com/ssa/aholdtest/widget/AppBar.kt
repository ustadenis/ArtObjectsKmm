package com.ssa.aholdtest.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String,
    showBackButton: Boolean = true,
    icon: ImageVector? = null,
    onClick: () -> Unit = {}
) {
    val rootController = LocalRootController.current
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        contentPadding = PaddingValues(0.dp),
        elevation = 0.dp
    ) {
        if (showBackButton) {
            IconButton(
                modifier = Modifier.padding(vertical = 18.dp)
                    .padding(start = 24.dp)
                    .size(20.dp),
                onClick = { rootController.popBackStack() }
            ) {
                Icon(
                    Icons.Filled.ArrowBackIos,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        } else {
            Box(
                modifier = Modifier.padding(vertical = 18.dp)
                    .padding(start = 24.dp)
                    .size(20.dp)
            )
        }
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold)
            )
        }
        IconButton(
            modifier = Modifier.padding(vertical = 18.dp)
                .padding(end = 24.dp)
                .size(20.dp),
            onClick = onClick
        ) {
            if (icon != null) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    }
}