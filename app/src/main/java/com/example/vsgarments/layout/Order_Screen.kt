package com.example.vsgarments.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.vsgarments.ui.theme.appbackgroundcolor


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.example.vsgarments.ui.theme.splashdarkblue
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue

@Composable
fun Order_Screen(
    modifier: Modifier ,
    navController: NavController
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(appbackgroundcolor),
        verticalArrangement = Arrangement.Top ,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        BoxWithSemiCircularCutout()
    }
}

@Composable
fun BoxWithSemiCircularCutout() {
    Box(
        modifier = Modifier
            .height(150.dp) // Size of the box
            .fillMaxWidth()
            .drawBehind {
                val semicircleDiameter = with(density) { 100.dp.toPx() }
                val semicircleRadius = semicircleDiameter / 2

                val path = Path()

                // Start the path at the top-left corner of the cutout
                path.moveTo(0f, semicircleRadius)

                // Draw the left vertical line down to the bottom
                path.lineTo(0f, size.height)

                // Draw the bottom horizontal line across
                path.lineTo(size.width, size.height)

                // Draw the right vertical line up to the cutout level
                path.lineTo(size.width, semicircleRadius)

                // Move to the top edge of the semicircle
                path.lineTo(size.width / 2 + semicircleRadius, semicircleRadius)

                // Create the semicircular arc
                path.arcTo(
                    Rect(
                        left = size.width / 2 - semicircleRadius,
                        top = 0f,
                        right = size.width / 2 + semicircleRadius,
                        bottom = semicircleDiameter
                    ),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 180f,
                    forceMoveTo = false
                )

                // Close the path to finish the shape
                path.lineTo(0f, semicircleRadius)
                path.close()

                // Draw the path on the canvas
                drawPath(
                    path = path,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            topbarlightblue,
                            topbardarkblue
                        )
                    )
                )
            }
    ) {
        // Content of the box goes here
    }
}
