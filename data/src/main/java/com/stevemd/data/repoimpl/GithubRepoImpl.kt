package com.stevemd.data.repoimpl

import com.stevemd.common.model.RepoItemEntity
import com.stevemd.common.model.UserProfileEntity
import com.stevemd.data.githubapiservice.ApiService
import com.stevemd.data.githubapiservice.NetworkBoundResource
import com.stevemd.data.mappers.RepositoryListItemMapper
import com.stevemd.data.mappers.UserProfileMapper
import com.stevemd.data.mappers.mapFromApiResponse
import com.stevemd.domain.repository.GithubRepository
import com.stevemd.domain.usecases.RepoListUseCase
import com.stevemd.domain.usecases.UserProfileUseCase
import com.stevemd.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val networkBoundResource: NetworkBoundResource,
    private val repositoryListItemMapper: RepositoryListItemMapper,
    private val userProfileMapper: UserProfileMapper
) : GithubRepository {
    override suspend fun fetchRepoList(params: RepoListUseCase.Params): Flow<Result<List<RepoItemEntity>>> {
        return mapFromApiResponse(
            result = networkBoundResource.downloadData {
                apiService.fetchRepoList(params.userName)
            },repositoryListItemMapper
        )
    }

    override suspend fun fetchOwnerProfile(params: UserProfileUseCase.Params): Flow<Result<UserProfileEntity>> {
        return mapFromApiResponse(
            result = networkBoundResource.downloadData {
                apiService.fetchUser(params.userName)
            },userProfileMapper
        )
    }
}