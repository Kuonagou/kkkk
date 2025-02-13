package com.example.kkkk.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.kkkk.R
import com.example.kkkk.navigation.Screen

@Composable
fun SplashScreenSun(navHostController: NavHostController) {
    val raw = R.raw.soleil
    val darkMode = !isSystemInDarkTheme();
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(raw))
    val progress by animateLottieCompositionAsState(composition = composition)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight().background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Agrandissement de l'animation avec Modifier.size()
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(300.dp) // Taille personnalisée
        )
        Text("What's our Date",fontSize = 30.sp,color =  Color.Black, fontStyle = FontStyle.Italic,fontWeight = FontWeight.Bold)
    }

    if (progress == 1f) {
        LaunchedEffect(Unit) {
            navHostController.navigate(Screen.PageAccueil.route) {
                popUpTo("SplashScreenBeer") { inclusive = true } // Supprime SplashScreen de l'historique
            }
        }
    }
}