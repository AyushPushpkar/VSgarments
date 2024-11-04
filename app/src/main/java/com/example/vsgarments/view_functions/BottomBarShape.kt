package com.example.vsgarments.view_functions

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
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

        fun withDensity(value: Float): Float {
            return with(density) { value.dp.toPx() }
        }

        val path = Path()

        path.moveTo(x = 0f, y = 0f)
        path.lineTo(x = 0f, y = height)
        path.lineTo(x = width, y = height)
        path.lineTo(x = width, y = 0f)

        val cutoutPosition = cutoutCenter.coerceIn(sidelength, width - sidelength)

        path.lineTo(x = cutoutPosition + sidelength + withDensity(40f), y = withDensity(0f))

        path.cubicTo(
            x1 = cutoutPosition + sidelength + withDensity(20f), y1 = withDensity(0f),
            x2 = cutoutPosition + sidelength + withDensity(5f), y2 = withDensity(0f),
            x3 = cutoutPosition + sidelength, y3 = withDensity(15f)
        )

        path.cubicTo(
            x1 = cutoutPosition + sidelength - withDensity(20f), y1 = withDensity(60f),
            x2 = cutoutPosition - sidelength + withDensity(20f), y2 = withDensity(60f),
            x3 = cutoutPosition - sidelength, y3 = withDensity(15f)
        )

        path.cubicTo(
            x1 = cutoutPosition - sidelength - withDensity(5f), y1 = withDensity(0f),
            x2 = cutoutPosition - sidelength - withDensity(20f), y2 = withDensity(0f),
            x3 = cutoutPosition - sidelength - withDensity(40f), y3 = withDensity(0f)
        )

        path.lineTo(0f, 0f)
        path.close()

        return Outline.Generic(path)
    }
}
