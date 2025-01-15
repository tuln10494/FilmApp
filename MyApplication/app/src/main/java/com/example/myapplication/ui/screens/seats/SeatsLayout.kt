package com.example.myapplication.ui.screens.seats

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.myapplication.models.seatselection.SeatsRowData


@Composable
fun SeatsLayout(seats: List<SeatsRowData>, isReverse: Boolean = false) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    BoxWithConstraints {
        val onGesture = { _: Offset, offsetChange: Offset, zoomChange: Float, _: Float,
                          _: PointerInputChange, changes: List<PointerInputChange> ->
            scale = (scale * zoomChange).coerceIn(1f, 3f)

            val extraWidth = (scale - 1) * constraints.maxWidth
            val extraHeight = (scale - 1) * 200

            val maxX = extraWidth / 2
            val maxY = extraHeight / 2

            offset = Offset(
                x = (offset.x + scale * offsetChange.x).coerceIn(-maxX, maxX),
                y = (offset.y + scale * offsetChange.y).coerceIn(-maxY, maxY)
            )

            if (changes.size > 1) {
                changes.forEach { it.consume() }
            }
        }

        Row(modifier = Modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                translationX = offset.x
                translationY = offset.y
            }
            .pointerInput(Unit) {
                detectTransformGestures(
                    pass = PointerEventPass.Initial,
                    onGesture = onGesture
                )
            }) {
            val direction = if (isReverse) LayoutDirection.Rtl else LayoutDirection.Ltr

            CompositionLocalProvider(LocalLayoutDirection provides direction) {
                Column(horizontalAlignment = Alignment.Start) {
                    seats.forEach {
                        SeatsRow(it)
                    }
                }
            }
        }
    }
}