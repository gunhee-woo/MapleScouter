package com.example.maplescouter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maplescouter.domain.model.ApiResult
import com.maplescouter.domain.model.CharacterInfo
import com.maplescouter.domain.usecase.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : ViewModel() {

    private val _characterListState = MutableStateFlow<ApiResult<List<CharacterInfo>>>(ApiResult.Loading)
    val characterListState: StateFlow<ApiResult<List<CharacterInfo>>> = _characterListState

    init {
        fetchCharacterList()
    }

    fun fetchCharacterList() {
        Log.d("MainViewModel", "Fetching character list...")
        viewModelScope.launch {
            _characterListState.value = ApiResult.Loading
            _characterListState.value = getCharacterListUseCase()
        }
    }
}
