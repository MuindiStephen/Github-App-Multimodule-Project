package com.stevemd.common.model

data class RepoItemEntity(
    val userAvatarUrl:String,
    val userName:String,
    val repoName:String,
    val repoFullName:String,
    val repoDescription:String,
    val language:String,
    val forksCount:Int,
    val stargazers_count:Int,
)