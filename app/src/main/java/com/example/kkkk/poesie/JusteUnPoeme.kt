package com.example.kkkk.poesie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kkkk.R
import com.example.kkkk.data.PoemRepository
import com.example.kkkk.navigation.Screen
import com.example.kkkk.ui.theme.PastelGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JusteUnPoeme(navHostController: NavHostController,id :Int) {
    val context = LocalContext.current
    val repository = PoemRepository(context)
    val poeme = repository.getThisPoeme(id)

    val backgroundColor = poeme?.color ?: PastelGreen // Assigne la couleur du poème ou une couleur par défaut

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
                Text(text = poeme.title, fontSize = 24.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = poeme.content, fontSize = 18.sp, color = Color.Black)
            } else {
                Text(
                    text = "Tous les poèmes ont déjà été tirés pour l'instant, mais d'autres seront ajoutés plus tard rien que pour toi !",
                    fontSize = 24.sp,
                    color = Color.Black
                )
            }
        }
    }
}
