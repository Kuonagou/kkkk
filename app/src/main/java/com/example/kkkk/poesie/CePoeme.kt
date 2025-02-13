package com.example.kkkk.poesie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.kkkk.R
import com.example.kkkk.data.PoemeRepository
import com.example.kkkk.navigation.Screen
import com.example.kkkk.ui.theme.PastelGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CePoeme(navHostController: NavHostController) {

    val context = LocalContext.current
    val repository = remember { PoemeRepository(context) }
    val factory = remember { PoemeViewModelFactory(repository) }
    val viewModel: PoemeViewModel = viewModel(factory = factory)
    // Charger un poème aléatoire non pris
    LaunchedEffect(Unit) {
        viewModel.getRandomPoeme()
    }

    // Observer le poème sélectionné
    val poeme by viewModel.selectedPoeme.observeAsState()

    val backgroundColor = poeme?.color ?: PastelGreen
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = backgroundColor, // Assigne la couleur du Scaffold
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(text = stringResource(id = R.string.home_title))
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigate(Screen.TesPoemes.route) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.home_settings),
                            tint = Color.Black,
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(20.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (poeme != null) {
                Text(text = poeme!!.title, fontSize = 24.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = poeme!!.description, fontSize = 18.sp, color = Color.Black)
            } else {
                Text(
                    text = "Hey ho ! C'est seulement un poème par jour, reviens demain! Biz",
                    fontSize = 24.sp,
                    color = Color.Black
                )
                Text(
                    text = "Si cela fait plusieurs jours désolé c'est qu'il n'y en a plus ( j'en remettrais très bientôt )",
                    fontSize = 10.sp,
                    color = Color.Black
                )
            }
        }
    }
}
