package com.example.paginationchallenge.ui.mainscreen

import androidx.paging.PagingData
import com.example.paginationchallenge.domain.model.MarvelCharacter
import com.example.paginationchallenge.domain.repository.MarvelCharactersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class CharacterListPresenter(
    private val view: CharacterListContract.View,
    private val repository: MarvelCharactersRepository
) : CharacterListContract.Presenter {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var _characters: Flow<PagingData<MarvelCharacter>> = MutableStateFlow(PagingData.empty())
    val characters: Flow<PagingData<MarvelCharacter>>
        get() = _characters

//    private val _character: MutableState<MarvelCharacter> = mutableStateOf(MarvelCharacter())
//    val character: MarvelCharacter
//        get() = _character.value


//    fun loadCharacters() {
//        coroutineScope.launch {
//            characters2.value = repository.getCharacters2()?.data?.results?.map { it.asCharacter() }
//            _character.value = repository.findCharacter()?.data?.results?.get(0)?.asCharacter()
//                ?: MarvelCharacter()
//        }
//    }

    override fun onViewCreated() {
        coroutineScope.launch {
            val characterList = repository.getCharacters()
            _characters = characterList
            view.showCharacters(characterList)
        }
    }

    override fun onCharacterClick(click: () -> Unit) {}


}

interface CharacterListContract {
    interface View {
        fun showCharacters(characters: Flow<PagingData<MarvelCharacter>>)
    }

    interface Presenter {
        fun onViewCreated()
        fun onCharacterClick(click: () -> Unit)
    }
}