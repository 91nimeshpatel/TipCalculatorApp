package com.nimeshpatel.tipcalculator.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by Nimesh Patel on 6/3/2024.
 * Purpose:
 */
object CustomIcon {
    @Composable
    fun RoundedCornerIcon(
        modifier: Modifier = Modifier,
        imageVector: ImageVector,
        contentDescription: String,
        onClick: () -> Unit,
        tint: Color = Color.Black.copy(alpha = 0.8f),
        elevation: Dp = 4.dp
    ) {
        val iconButtonSizeModifier = modifier.size(40.dp)
        Card(
            modifier = modifier
                .clickable {
                    onClick.invoke()
                }
                .then(iconButtonSizeModifier),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = elevation)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = contentDescription,
                    tint = tint
                )
            }
        }
    }
}