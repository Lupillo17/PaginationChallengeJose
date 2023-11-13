package com.example.paginationchallenge.domain

class GetMarvelCharacters(private val repository: MarvelCharactersRepository) {
    operator fun invoke() = repository.getCharacters()
}