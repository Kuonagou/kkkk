package com.example.kkkk.stat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TimelineItem(
    phrase: String,
    date: Date?,
    color: Color,
    isFirst: Boolean,
    isLast: Boolean,
    isLeftAligned: Boolean
) {
    val lineColor = Color.Gray.copy(alpha = 0.3f)
    val circleSize = 16.dp
    val lineWidth = 2.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        // Timeline line
        if (!isFirst) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .width(lineWidth)
                    .height(24.dp)
                    .offset(y = (-24).dp)
                    .background(lineColor)
            )
        }

        if (!isLast) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .width(lineWidth)
                    .height(24.dp)
                    .offset(y = 24.dp)
                    .background(lineColor)
            )
        }

        // Circle node
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(circleSize)
                .background(color, shape = CircleShape)
        )

        // Content card
        Card(
            modifier = Modifier
                .align(if (isLeftAligned) Alignment.CenterStart else Alignment.CenterEnd)
                .padding(horizontal = 32.dp)
                .width(700.dp),
            colors = CardDefaults.cardColors(
                containerColor = color.copy(alpha = 0.8f),
                contentColor = Color.Black
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = phrase,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )

                date?.let {
                    val formatter = SimpleDateFormat("dd MMMM yyyy à HH:mm", Locale.FRENCH)
                    Text(
                        text = formatter.format(it),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}