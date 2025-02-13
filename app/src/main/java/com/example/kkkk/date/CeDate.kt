package com.example.kkkk.date

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
import com.example.kkkk.data.DateRepository
import com.example.kkkk.navigation.Screen
import java.util.Calendar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CeDate(phraseId: Int, navHostController: NavHostController) {
    val context = LocalContext.current
    val repository = remember { DateRepository(context) }
    val factory = remember { DateViewModelFactory(repository) }
    val viewModel: DateViewModel = viewModel(factory = factory)

    // Charger la date sélectionnée
    LaunchedEffect(phraseId) {
        viewModel.getDateById(phraseId)
    }

    // Observer la date sélectionnée
    val phraseData by viewModel.selectedDate.observeAsState()
    var isCompleted by remember { mutableStateOf(false) }

    // Mettre à jour isCompleted lorsque phraseData change
    LaunchedEffect(phraseData) {
        isCompleted = phraseData?.dateDudate != null
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = phraseData?.color ?: Color.White,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(text = stringResource(id = R.string.home_title))
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigate(Screen.ListeDate.route) }) {
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
                .padding(innerPadding)
        ) {
            phraseData?.let { data ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = data.color,
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = data.title,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(20.dp)
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = data.color,
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = data.description,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(20.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        viewModel.updateDateDate(data.id, Calendar.getInstance().time)
                        isCompleted = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = data.color),
                    enabled = !isCompleted,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = if (isCompleted) "Merci pour ta merveilleuse participation ^^" else "J'ai réalisé cette activité !")
                }
            } ?: run {
                Text(
                    text = "❌ Phrase non trouvée",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
    }
}