package com.ssa.aholdtest.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingProgress() {
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 24.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp).align(Alignment.Center)
        )
    }
}