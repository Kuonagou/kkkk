package com.example.kkkk.poesie
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
fun TesPoemes(navHostController: NavHostController) {
    val context = LocalContext.current
    val repository = PoemRepository(context)
    val drawnPoems = repository.getDrawnPoems().mapNotNull { id -> repository.getAllPoems().find { it.id == id } }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PastelGreen,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(text = stringResource(id = R.string.home_title))
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigate(Screen.PageAccueil.route) }) {
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
        Column(modifier = Modifier.fillMaxSize().padding(20.dp).padding(innerPadding)) {

            Text("Poèmes déjà tirés :", fontSize = 24.sp, color = Color.Black)

            drawnPoems.forEach { poeme ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clickable {
                            navHostController.navigate(Screen.JusteUnPoeme.createRoutePoeme(poeme.id))
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = poeme.color,
                        contentColor = Color.Black
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(poeme.title, fontSize = 20.sp, color = Color.Black)
                    }
                }

            }
        }
    }
}
