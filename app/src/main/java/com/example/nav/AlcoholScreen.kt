package com.example.nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun AlcoholScreen(viewModel: AlcoholViewModel = viewModel()) {
    val cocktails by viewModel.cocktails.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // 3 елементи в ряд
        modifier = Modifier.fillMaxSize().padding(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(cocktails) { cocktail ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = cocktail.strDrinkThumb,
                        contentDescription = "Cocktail Image",
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = cocktail.strDrink,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}
