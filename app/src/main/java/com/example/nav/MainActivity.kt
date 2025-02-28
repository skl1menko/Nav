package com.example.nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nav.ui.theme.NavTheme
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel


enum class Tab(val label:String, val icon: Int ){
    Alc("Alcohol",R.drawable.alc),
    No_Alc("NoAlcohol",R.drawable.no_alc)
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavTheme {
                val viewModel: MainViewModel = viewModel()
                MainScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val currentTab by viewModel.currentTab.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TopAppBar(
                title = { Text(text = currentTab.label) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                color = MaterialTheme.colorScheme.background
            ) {
                TabScreen(tab = currentTab)
            }

            NavigationBar {
                Tab.values().forEach { tab ->
                    NavigationBarItem(
                        selected = tab == currentTab,
                        onClick = { viewModel.onTabSelected(tab) },
                        label = { Text(text = tab.label) },
                        icon = {
                            Icon(
                                painter = painterResource(tab.icon),
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AlcScreen() {
    Box {
        Text(text = "Alcohol Screen")
    }
}

@Composable
fun NoAlcScreen() {
    Box {
        Text(text = "No-Alcohol Screen")
    }
}

@Composable
fun TabScreen(tab: Tab) {
    when (tab) {
        Tab.Alc -> AlcScreen()
        Tab.No_Alc -> NoAlcScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    NavTheme {
        MainScreen(viewModel())
    }
}
