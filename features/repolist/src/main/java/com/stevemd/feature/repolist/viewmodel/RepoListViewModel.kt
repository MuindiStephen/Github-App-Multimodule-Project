package com.stevemd.feature.repolist.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stevemd.common.base.BaseViewModel
import com.stevemd.common.model.RepoItemEntity
import com.stevemd.domain.usecases.RepoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import com.stevemd.domain.utils.Result
import kotlinx.coroutines.launch

@HiltViewModel
class RepoListViewModel  @Inject constructor(
    private val repoListUseCase: RepoListUseCase
): ViewModel() {
    val action: (RepoListUiAction) -> Unit

    private val _uiState = MutableStateFlow<RepoListUiState<Any>>(RepoListUiState.Loading(isLoading = true))
    val uiState: StateFlow<RepoListUiState<Any>> get() = _uiState


    init {
        action = {
            when(it){
                is RepoListUiAction.FetchRepoList -> fetchRepoList(it)
            }
        }
        action(RepoListUiAction.FetchRepoList())
    }

    private fun fetchRepoList(data:RepoListUiAction.FetchRepoList){
//        execute {
//            repoListUseCase.execute(RepoListUseCase.Params(userName = data.userName)).collect{result->
//                when(result){
//                    is Result.Success-> _uiState.value = RepoListUiState.Success(result.data)
//                    is Result.Error-> _uiState.value = RepoListUiState.Error(result.message)
//                    is Result.Loading -> _uiState.value = RepoListUiState.Loading(result.loading)
//                }
//            }
//        }

        viewModelScope.launch {
                        repoListUseCase.execute(RepoListUseCase.Params(userName = data.userName)).collect{result->
                when(result){
                    is Result.Success-> _uiState.value = RepoListUiState.Success(result.data)
                    is Result.Error-> _uiState.value = RepoListUiState.Error(result.message)
                    is Result.Loading -> _uiState.value = RepoListUiState.Loading(result.loading)
                }
            }
        }
    }
}

sealed class RepoListUiState<out R>{
    data class Loading(val isLoading:Boolean):RepoListUiState<Boolean>()
    data class Error(val message:String):RepoListUiState<String>()
    data class Success(val data:List<RepoItemEntity>):RepoListUiState<List<RepoItemEntity>>()
}

sealed class RepoListUiAction{
    data class FetchRepoList(val userName:String = "MuindiStephen"):RepoListUiAction()
}