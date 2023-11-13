package com.example.paginationchallenge.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.paginationchallenge.domain.GetMarvelCharacters

class MainScreenViewModel(getCharacters: GetMarvelCharacters) : ViewModel() {
    val characters = getCharacters().cachedIn(viewModelScope)
}