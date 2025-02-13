package com.example.kkkk.accueil

import android.content.Context
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import com.example.kkkk.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kkkk.navigation.Screen
import com.example.kkkk.ui.theme.*
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceAsColor")
@Composable
fun PageAccueil(navHostController: NavHostController,context: Context) {
    val scrollState = rememberScrollState()

    // 🌟 Vérification du premier lancement avec SharedPreferences
    val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
    val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)
    var showPopup by remember { mutableStateOf(isFirstLaunch) }

    if (showPopup) {
        InfoPopup(
            onDismiss = {
                showPopup = false
                sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
            }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PastelBlue,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(text = stringResource(id = R.string.home_title))
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeCard("Parcourir nos options de date", PastelRed) {
                navHostController.navigate(Screen.ListeDate.route)
            }
            HomeCard("Tirer un date au hasard", PastelOrange) {
                val randomId = getRandomDateId()
                navHostController.navigate(Screen.CeDate.createRoute(randomId))
            }
            HomeCard("Suivi de nos aventures", PastelYellow) {
                navHostController.navigate(Screen.SuiviActivites.route)
            }
            HomeCard("Poème du jour", PastelPink) {
                navHostController.navigate(Screen.CePoeme.route)
            }
            HomeCard("Ta collection", PastelPurple) {
                navHostController.navigate(Screen.TesPoemes.route)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Informations complémentaires
            Text(
                text = "Dans ce petit monde:\n- Nos idées de dates.\n- Un petit suivi de nos réalisations.\n- De petites poésies cachées ",
                fontSize = 18.sp,
                color = Color.DarkGray
            )
        }
    }
}

// 🌟 Composable pour la pop-up explicative
@Composable
fun InfoPopup(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Découvrir")
            }
        },
        title = {  Text("\uD83C\uDF1F Bonjour Klervie ! \uD83C\uDF1F") },
        text = {
            Text("Cette application a été créée spécialement pour toi alors j'espère qu'elle te plaira et que tu t'amusera à l'explorer.",)
        },
        shape = RoundedCornerShape(16.dp)
    )
}

// 🌟 Composable pour générer une carte cliquable
@Composable
fun HomeCard(text: String, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = Color.Black
        )
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(20.dp))
    }
}

fun getRandomDateId(): Int {
    val totalDates = 17 // Nombre total de dates enregistrées
    return Random.nextInt(1, totalDates + 1) // Génère un ID entre 1 et 17
}
