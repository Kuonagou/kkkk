package com.example.kkkk.stat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import java.util.Date

@Composable
fun TimelineItem(
    phrase: String,
    date: Date?,
    color: Color,
    isFirst: Boolean,
    isLast: Boolean,
    isLeftAligned: Boolean
) {
    // Une ligne qui sépare chaque élément de la timeline
    val lineColor = Color.Gray.copy(alpha = 0.3f)  // Couleur de la ligne
    val circleSize = 8.dp  // Taille des cercles aux extrémités de la ligne

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Lignes reliant les boîtes
        if (!isLast) {
            // Ligne reliant cet élément avec le suivant
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxHeight()
                    .width(2.dp)
                    .background(lineColor)
            )
        }

        // Cercles représentant la timeline
        Box(
            modifier = Modifier
                .align(if (isLeftAligned) Alignment.CenterStart else Alignment.CenterEnd)
                .size(circleSize)
                .background(color, shape = CircleShape)
        )

        // Contenu de chaque élément
        Card(
            modifier = Modifier
                .align(if (isLeftAligned) Alignment.CenterStart else Alignment.CenterEnd)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = color,
                contentColor = Color.Black
            ),
            elevation = CardDefaults.cardElevation(8.dp) // Élévation de la Card
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = phrase,
                    style = MaterialTheme.typography.bodyLarge, // Utilisation du style correct
                    color = Color.Black
                )
                Text(
                    text = date.toString(),
                    style = MaterialTheme.typography.bodyMedium, // Utilisation du style correct
                    color = Color.Gray
                )
            }
        }
    }
}
