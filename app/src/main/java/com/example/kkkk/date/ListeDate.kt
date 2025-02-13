package com.example.kkkk.date

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.kkkk.data.DateRepository
import com.example.kkkk.navigation.Screen
import com.example.kkkk.ui.theme.PastelGreen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListeDate(navHostController: NavHostController) {
    val context = LocalContext.current
    val repository = remember { DateRepository(context) }
    val factory = remember { DateViewModelFactory(repository) }
    val viewModel: DateViewModel = viewModel(factory = factory)

    val dates by viewModel.allDates.observeAsState(initial = emptyList())

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .padding(innerPadding)
        ) {
            LazyColumn {
                items(dates) { date ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .clickable {
                                navHostController.navigate(Screen.CeDate.createRoute(date.id))
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = date.color,
                            contentColor = Color.Black
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("${date.id}. ${date.title}", fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
}