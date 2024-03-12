package com.stevemd.data.githubapiservice

import com.stevemd.common.dto.RepoItemApiResponse
import com.stevemd.common.dto.UserProfileApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService  {
    @GET("/users/{username}/repos")
    suspend fun fetchRepoList(
        @Path("username")username:String
    ): Response<List<RepoItemApiResponse>>

    @GET("/users/{username}")
    suspend fun fetchUser(
        @Path("username")username:String
    ):Response<UserProfileApiResponse>
}