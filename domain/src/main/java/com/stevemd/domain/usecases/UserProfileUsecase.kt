package com.stevemd.domain.usecases

import com.stevemd.common.model.UserProfileEntity
import com.stevemd.domain.repository.GithubRepository
import com.stevemd.domain.utils.ApiUseCaseParams
import com.stevemd.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserProfileUseCase @Inject constructor(
    private val repository: GithubRepository
):ApiUseCaseParams<UserProfileUseCase.Params, UserProfileEntity>{
    data class Params(val userName:String)
    override suspend fun execute(params: Params): Flow<Result<UserProfileEntity>> {
        return repository.fetchOwnerProfile(params)
    }
}