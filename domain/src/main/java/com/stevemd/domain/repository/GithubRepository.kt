package com.stevemd.domain.repository

import com.stevemd.common.model.RepoItemEntity
import com.stevemd.common.model.UserProfileEntity
import com.stevemd.domain.usecases.RepoListUseCase
import com.stevemd.domain.usecases.UserProfileUseCase
import com.stevemd.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun fetchRepoList(params: RepoListUseCase.Params): Flow<Result<List<RepoItemEntity>>>
    suspend fun fetchOwnerProfile(params: UserProfileUseCase.Params):Flow<Result<UserProfileEntity>>
}