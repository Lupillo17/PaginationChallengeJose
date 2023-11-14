package com.example.paginationchallenge.ui.mainscreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.paginationchallenge.data.asCharacter
import com.example.paginationchallenge.data.remote.ApiResponse
import com.example.paginationchallenge.domain.GetMarvelCharacters
import com.example.paginationchallenge.domain.model.MarvelCharacter
import com.example.paginationchallenge.domain.repository.MarvelCharactersRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class MainScreenPresenter(
//    private val view: MainScreenContract.View,
    private val repository: MarvelCharactersRepository,
    getCharacters: GetMarvelCharacters
) : ViewModel() {
    val characters = getCharacters().cachedIn(viewModelScope)
    var characters2: List<MarvelCharacter>? = emptyList()

    fun loadData(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            characters2 = repository.getCharacters2()?.data?.results?.map { it.asCharacter() }
        }
    }

//    override fun onViewCreated() {
//        view.showLoading()
//        viewModelScope.launch(Dispatchers.IO) {
//            characters = repository.getCharacters2()?.data?.results?.map { it.asCharacter() }
//        }
//    }
//
//    override fun onCharacterClick(characterId: Int) {
//        TODO("Not yet implemented")
//    }


}

interface MainScreenContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showCharacters(characters: List<MarvelCharacter>)
        fun navigateToCharacterDetails(characterId: Int)
    }

    interface Presenter {
        fun onViewCreated()
        fun onCharacterClick(characterId: Int)
    }
}