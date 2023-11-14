package com.example.paginationchallenge.domain

import com.example.paginationchallenge.domain.repository.MarvelCharactersRepository

class GetMarvelCharacters(private val repository: MarvelCharactersRepository) {
    operator fun invoke() = repository.getCharacters()
}