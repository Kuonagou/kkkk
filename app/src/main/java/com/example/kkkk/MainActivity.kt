package com.example.kkkk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.kkkk.data.*
import com.example.kkkk.navigation.SetupNavGraph
import com.example.kkkk.ui.theme.KkkkTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class,ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            // Enable edge-to-edge display
            enableEdgeToEdge()

            // Set up the main content
        setContent {
            // Le NavController doit être créé ici dans le composable
            val navController = rememberNavController()

            KkkkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SetupNavGraph(
                        modifier = Modifier.padding(innerPadding),
                        navHostController = navController,
                        context = this
                    )
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KkkkTheme {
        Greeting("Android")
    }
}