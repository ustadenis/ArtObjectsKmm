package com.ssa.aholdtest.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = false, //isSystemInDarkTheme(), // Uncomment when dark theme will be ready
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        // TODO: dark theme
        darkColors(
            primary = Color(0xFF006FFD),
            secondary = Color(0xFF6FBAFF),
            secondaryVariant = Color(0xFFD4D6DD),
            onPrimary = Color(0xFF2F3036),
            onSecondary = Color.White,
            surface = Color(0xFFF8F9FE),
            onSurface = Color(0xFF2F3036),
            background = Color(0xFFFCFCFC),
        )
    } else {
        lightColors(
            primary = Color(0xFF006FFD),
            secondary = Color(0xFF6FBAFF),
            secondaryVariant = Color(0xFFD4D6DD),
            onPrimary = Color(0xFF2F3036),
            onSecondary = Color.White,
            surface = Color(0xFFF8F9FE),
            onSurface = Color(0xFF2F3036),
            background = Color(0xFFFCFCFC),
        )
    }
    val typography = Typography(
        h1 = TextStyle(
            color = colors.onPrimary,
            fontWeight = FontWeight.Light,
            fontSize = 96.sp,
            letterSpacing = (-1.5).sp
        ),
        h2 = TextStyle(
            color = colors.onPrimary,
            fontWeight = FontWeight.Light,
            fontSize = 60.sp,
            letterSpacing = (-0.5).sp
        ),
        h3 = TextStyle(
            color = colors.onPrimary,
            fontWeight = FontWeight.Normal,
            fontSize = 48.sp,
            letterSpacing = 0.sp
        ),
        h4 = TextStyle(
            color = colors.onPrimary,
            fontWeight = FontWeight.Normal,
            fontSize = 34.sp,
            letterSpacing = 0.25.sp
        ),
        h5 = TextStyle(
            color = colors.onPrimary,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            letterSpacing = 0.sp
        ),
        h6 = TextStyle(
            color = colors.onPrimary,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            letterSpacing = 0.15.sp
        ),
        subtitle1 = TextStyle(
            color = colors.onPrimary,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.15.sp
        ),
        subtitle2 = TextStyle(
            color = colors.onPrimary,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.1.sp
        ),
        body1 = TextStyle(
            color = colors.onPrimary,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp
        ),
        body2 = TextStyle(
            color = colors.onPrimary,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.25.sp
        ),
        button = TextStyle(
            color = colors.onPrimary,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 1.25.sp
        ),
        caption = TextStyle(
            color = colors.onPrimary,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            letterSpacing = 0.4.sp
        ),
        overline = TextStyle(
            color = colors.onPrimary,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            letterSpacing = 1.5.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
