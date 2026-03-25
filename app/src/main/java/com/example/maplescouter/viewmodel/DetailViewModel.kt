package com.example.maplescouter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maplescouter.domain.model.ApiResult
import com.maplescouter.domain.model.CharacterBasic
import com.maplescouter.domain.model.CharacterInfo
import com.maplescouter.domain.usecase.GetCharacterBasicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterBasicUseCase: GetCharacterBasicUseCase
) : ViewModel() {

    private val _characterBasicState = MutableStateFlow<ApiResult<CharacterBasic>>(ApiResult.Loading)
    val characterBasicState: StateFlow<ApiResult<CharacterBasic>> = _characterBasicState

    fun fetchCharacterBasic(name: String) {
        viewModelScope.launch {
            _characterBasicState.value = ApiResult.Loading
            _characterBasicState.value = getCharacterBasicUseCase(name)
        }
    }
}
