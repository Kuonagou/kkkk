package com.example.kkkk.stat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.kkkk.data.DateRepository
import com.example.kkkk.navigation.Screen
import com.example.kkkk.ui.theme.PastelBlue
import com.example.kkkk.date.DateViewModel
import com.example.kkkk.date.DateViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuiviActivites(navHostController: NavHostController) {
    val context = LocalContext.current
    val repository = DateRepository(context)
    val factory = DateViewModelFactory(repository)
    val viewModel: DateViewModel = viewModel(factory = factory)

    val completedActivities by viewModel.completedActivities.observeAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = PastelBlue,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(text = "Suivi des activités")
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigate(Screen.PageAccueil.route) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Retour",
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
                .padding(innerPadding)
        ) {
            if (completedActivities.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(completedActivities) { index, date ->
                        TimelineItem(
                            phrase = date.title,
                            date = date.dateDudate,
                            color = date.color,
                            isFirst = index == 0,
                            isLast = index == completedActivities.size - 1,
                            isLeftAligned = index % 2 == 0
                        )
                    }
                }
            } else {
                Text(
                    text = "Tu n'as complété aucune activité pour le moment quel dommage !",
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize(),
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}