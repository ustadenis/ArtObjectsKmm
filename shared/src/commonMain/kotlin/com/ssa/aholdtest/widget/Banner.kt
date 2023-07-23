package com.ssa.aholdtest.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ssa.aholdtest.common.bannerBackground
import com.ssa.aholdtest.common.onSurfaceLight

@Composable
fun InfoBanner(
    modifier: Modifier,
    title: String,
    content: String,
    icon: @Composable () -> Unit,
    buttons: @Composable RowScope.() -> Unit = {},
    background: Color = MaterialTheme.colors.bannerBackground
) {
    Box(
        modifier = modifier.wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(background)
    ) {
        Row(
            modifier = Modifier.padding(20.dp).wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(0.7f)
            ) {
                Content(title, content, buttons)
            }
            Spacer(modifier = Modifier.size(16.dp))
            Box(
                modifier = Modifier.width(100.dp).weight(0.3f),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }
        }
    }
}

@Composable
private fun ColumnScope.Content(
    title: String,
    content: String,
    buttons: @Composable RowScope.() -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold)
    )
    Spacer(modifier = Modifier.size(4.dp))
    Text(
        text = content,
        style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.onSurfaceLight)
    )
    Row(
        modifier = Modifier.wrapContentWidth().padding(top = 16.dp),
        horizontalArrangement = Arrangement.Start
    ) { buttons() }
}