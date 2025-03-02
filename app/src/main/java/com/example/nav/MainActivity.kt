package com.example.nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nav.ui.theme.NavTheme

enum class Tab(val label: String, val icon: Int) {
    Alc("Alcoholic", R.drawable.alc),
    No_Alc("Non-Alcoholic", R.drawable.no_alc)
}

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val DrinkViewModel: DrinkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavTheme {
                MainScreen(mainViewModel,DrinkViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    drinkViewModel: DrinkViewModel
) {
    val currentTab by mainViewModel.currentTab.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                Tab.values().forEach { tab ->
                    NavigationBarItem(
                        selected = tab == currentTab,
                        onClick = { mainViewModel.onTabSelected(tab) },
                        label = { Text(text = tab.label) },
                        icon = { Icon(painter = painterResource(tab.icon), contentDescription = null) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TopAppBar(title = { Text(text = currentTab.label) })

            when (currentTab) {
                Tab.Alc -> DrinkScreen(isAlcoholic = true, viewModel = drinkViewModel)
                Tab.No_Alc -> DrinkScreen(isAlcoholic = false, viewModel = drinkViewModel)
            }
        }
    }
}
