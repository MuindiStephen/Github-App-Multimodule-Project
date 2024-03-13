package com.stevemd.data.mappers

import com.stevemd.common.dto.RepoItemApiResponse
import com.stevemd.common.model.RepoItemEntity
import javax.inject.Inject

class RepositoryListItemMapper @Inject constructor():BaseMapper<List<RepoItemApiResponse>,List<RepoItemEntity>>{


    override fun mapFromApiResponse(type: List<RepoItemApiResponse>): List<RepoItemEntity> {
        return type.map {
            RepoItemEntity(
                userAvatarUrl = it.owner?.avatar_url ?: "https://www.pullrequest.com/blog/github-code-review-service/images/github-logo_hub2899c31b6ca7aed8d6a218f0e752fe4_46649_1200x1200_fill_box_center_2.png",
                userName = it.owner?.login ?: "NO_USER_NAME_FOUND",
                repoName = it.full_name ?: "EMPTY_REPO_NAME",
                repoFullName = it.full_name ?: "EMPTY_REPO_NAME",
                repoDescription = it.description ?: "No description found",
                language = it.language ?: "Not Found",
                forksCount = it.forks_count ?: 0,
                stargazers_count = it.stargazers_count ?: 0
            )
        }
    }

}