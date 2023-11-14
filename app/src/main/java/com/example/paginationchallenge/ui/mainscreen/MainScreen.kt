package com.example.paginationchallenge.ui.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.paginationchallenge.core.utils.ListPagingSource
import com.example.paginationchallenge.domain.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(onClick: (MarvelCharacter) -> Unit) {
    val viewModel: MainScreenPresenter = getViewModel()
    val context = LocalContext.current

    LaunchedEffect(true ){
        viewModel.loadData(context)
    }

    MainScreenComposable(viewModel.characters, onClick)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainScreenComposable(
    characters: Flow<PagingData<MarvelCharacter>>,
    onClick: (MarvelCharacter) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pagination Challenge") }
            )
        }
    ) { padding ->
        val characters: LazyPagingItems<MarvelCharacter> = characters.collectAsLazyPagingItems()
        val swipeRefreshState =
            rememberPullRefreshState(
                refreshing = characters.loadState.refresh == LoadState.Loading,
                onRefresh = { characters.refresh() }
            )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(padding)
                .pullRefresh(state = swipeRefreshState, enabled = true)
        ) {
            GridOfCharacters(characters, padding, onClick)
        }
    }
}

@Composable
private fun GridOfCharacters(
    characters: LazyPagingItems<MarvelCharacter>,
    padding: PaddingValues,
    onClick: (MarvelCharacter) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier.padding(padding)
    ) {
        items(characters.itemCount) { index ->
            val character = characters[index] ?: return@items
            CharacterItem(
                character = character,
                modifier = Modifier.clickable { onClick(character) }
            )
        }

        if (characters.loadState.append == LoadState.Loading) {
            items(2) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
    if (characters.loadState.refresh == LoadState.Loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun CharacterItem(character: MarvelCharacter, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Card {
            Image(
                painter = rememberAsyncImagePainter(model = character.image),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .aspectRatio(ratio = 0.67f),
            )
        }
        Box(
            modifier = Modifier
                .padding(8.dp, 16.dp)
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2
            )
        }
    }
}
