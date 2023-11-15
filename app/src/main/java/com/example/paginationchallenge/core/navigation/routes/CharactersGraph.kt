package com.example.paginationchallenge.core.navigation.routes

import com.example.paginationchallenge.core.utils.Constants.CHARACTER_DETAIL
import com.example.paginationchallenge.core.utils.Constants.CHARACTER_LIST

sealed class CharactersGraph(
    val route: String
) {
    object CharactersList : CharactersGraph(
        route = CHARACTER_LIST
    )

    object CharactersDetail : CharactersGraph(
        route = CHARACTER_DETAIL
    )
}