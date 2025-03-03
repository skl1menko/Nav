package com.example.nav

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.nav.model.Cocktail
@Composable
fun DrinkScreen(
    isAlcoholic: Boolean,
    viewModel: DrinkViewModel = viewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val cocktails by if (searchQuery.isEmpty()) {
        if (isAlcoholic) viewModel.alcoholCocktails.collectAsState()
        else viewModel.nonAlcoholCocktails.collectAsState()
    } else {
        viewModel.searchResults.collectAsState()
    }

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.searchCocktails(it)
            },
            label = { Text("Search Cocktail") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(cocktails) { cocktail ->
                CocktailItem(cocktail)
            }
        }
    }
}
@Composable
fun CocktailItem(cocktail: Cocktail) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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

