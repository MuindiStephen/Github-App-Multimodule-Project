package com.stevemd.domain.usecases

import com.stevemd.common.model.RepoItemEntity
import com.stevemd.domain.repository.GithubRepository
import com.stevemd.domain.utils.ApiUseCaseParams
import com.stevemd.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoListUseCase @Inject constructor(
    private val repository: GithubRepository
):ApiUseCaseParams<RepoListUseCase.Params,List<RepoItemEntity>>{
    override suspend fun execute(params: Params): Flow<Result<List<RepoItemEntity>>> {
        return repository.fetchRepoList(params)
    }
    data class Params(val userName:String)
}