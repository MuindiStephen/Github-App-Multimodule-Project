package com.stevemd.common.dto


data class RepoItemApiResponse(
    val description: String?,
    val forks_count: Int?,
    val full_name: String?,
    val owner: ReposItemOwner?,
    val pulls_url: String?,
    val pushed_at: String?,
    val releases_url: String?,
    val size: Int?,
    val ssh_url: String?,
    val stargazers_count: Int?,
    val language:String?
)
data class ReposItemOwner(
    val avatar_url: String?,
    val login: String?,
    )