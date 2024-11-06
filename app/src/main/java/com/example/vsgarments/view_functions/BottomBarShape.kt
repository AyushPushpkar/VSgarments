package com.example.vsgarments.view_functions

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class BottomBarShape(
    private val cutoutCenter: Float
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val sidelength = with(density) { 50.dp.toPx() }
        val width = size.width
        val height = size.height

        val extraSpace = with(density) { 40.dp.toPx() }
        val cutoutOffset20 = with(density) { 20.dp.toPx() }
        val cutoutOffset5 = with(density) { 5.dp.toPx() }
        val cutoutOffset15 = with(density) { 15.dp.toPx() }
        val cutoutOffset60 = with(density) { 60.dp.toPx() }

        val path = Path()

        path.moveTo(x = 0f, y = 0f)
        path.lineTo(x = 0f, y = height)
        path.lineTo(x = width, y = height)
        path.lineTo(x = width, y = 0f)

        val cutoutPosition = cutoutCenter.coerceIn(sidelength, width - sidelength)

        path.lineTo(x = cutoutPosition + sidelength + extraSpace, y = 0f)
        path.cubicTo(
            x1 = cutoutPosition + sidelength + cutoutOffset20, y1 = 0f,
            x2 = cutoutPosition + sidelength + cutoutOffset5, y2 = 0f,
            x3 = cutoutPosition + sidelength, y3 = cutoutOffset15
        )

        path.cubicTo(
            x1 = cutoutPosition + sidelength - cutoutOffset20, y1 = cutoutOffset60,
            x2 = cutoutPosition - sidelength + cutoutOffset20, y2 = cutoutOffset60,
            x3 = cutoutPosition - sidelength, y3 = cutoutOffset15
        )

        path.cubicTo(
            x1 = cutoutPosition - sidelength - cutoutOffset5, y1 = 0f,
            x2 = cutoutPosition - sidelength - cutoutOffset20, y2 = 0f,
            x3 = cutoutPosition - sidelength - extraSpace, y3 = 0f
        )

        path.lineTo(0f, 0f)
        path.close()

        return Outline.Generic(path)
    }
}
