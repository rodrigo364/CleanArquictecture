package br.com.cleanarquictecture.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.cleanarquictecture.ui.UiState
import br.com.core.Resource
import br.com.domain.model.CharacterAll
import br.com.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCase : CharacterUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<CharacterAll>>(UiState.Loading)
    val uiState: StateFlow<UiState<CharacterAll>> = _uiState
    private val _characterData = MutableLiveData<Resource<CharacterAll>>()
    val characterData: LiveData<Resource<CharacterAll>> = _characterData

    fun getCharactersAll() {

        viewModelScope.launch {
            charactersUseCase()
                .catch {
                    _uiState.value = UiState.Error(it.message)
                }
                .collect { resource ->
                _uiState.value = UiState.Success(resource.data)
            }
        }
    }
}