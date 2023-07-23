package com.ssa.aholdtest.common

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Colors.onSurfaceLight
    get() = if (isLight) {
        Color(0xFF8F9098)
    } else {
        Color(0xFF8F9098)
    }

val Colors.bannerBackground
    get() = if (isLight) {
        Color(0xFFEAF2FF)
    } else {
        Color(0xFFEAF2FF)
    }

val Colors.success
    get() = if (isLight) {
        Color(0xFF3AC0A0)
    } else {
        Color(0xFF3AC0A0)
    }

val switchColors: SwitchColors
    @Composable get() = SwitchDefaults.colors(
        uncheckedThumbColor = MaterialTheme.colors.surface,
        checkedThumbColor = MaterialTheme.colors.surface,
        checkedTrackColor = MaterialTheme.colors.primary,
        checkedTrackAlpha = 1f
    )