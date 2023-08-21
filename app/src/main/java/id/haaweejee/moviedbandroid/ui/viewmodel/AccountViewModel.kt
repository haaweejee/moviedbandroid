package id.haaweejee.moviedbandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haaweejee.moviedbandroid.domain.entities.AccountContentEntities
import id.haaweejee.moviedbandroid.domain.usecase.GetAccountContentUseCase
import id.haaweejee.moviedbandroid.ui.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getAccountContentUseCase: GetAccountContentUseCase,
) : ViewModel() {

    private val _accountContentState: MutableStateFlow<UiState<AccountContentEntities>> =
        MutableStateFlow(UiState.Loading)

    val accountContentState: MutableStateFlow<UiState<AccountContentEntities>> = _accountContentState

    fun getAccountDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            getAccountContentUseCase().catch {
                _accountContentState.value = UiState.Error(it.message.toString())
            }.collect {
                _accountContentState.value = UiState.Success(it)
            }
        }
    }
}
