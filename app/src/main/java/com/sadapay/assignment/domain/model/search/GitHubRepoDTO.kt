package com.sadapay.assignment.domain.model.search

import com.sadapay.assignment.data.remote.dto.search.Owner

data class GithubRepoRequestBody(
    val page: Int = 10, val size: Int = 1, val query: String = "language", val sortBy: String = "stars"
)

// domain Clean Model
data class GitHubRepo(
    val id: Int? = null,
    val name: String? = null,
    val full_name: String? = null,
    val private: Boolean? = null,
    val owner: Owner? = null,
    val description: String? = null,
    val score: Double? = null
)