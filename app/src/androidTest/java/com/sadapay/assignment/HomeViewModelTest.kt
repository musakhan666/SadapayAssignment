package com.sadapay.assignment

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import com.sadapay.assignment.domain.model.search.GitHubRepo
import com.sadapay.assignment.presentation.home.HomeViewModel
import com.sadapay.assignment.data.remote.ApiResult
import com.sadapay.assignment.data.remote.dto.search.Owner
import com.sadapay.assignment.domain.repository.MainRepository
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class HomeViewModelTest {

    @Mock
    private lateinit var repository: MainRepository

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun testGetTrendingGithubProfiles() = runBlocking {
        // Mock your repository's behavior here
        val dummyRepo1 = GitHubRepo(
            id = 1,
            name = "dummy-repo-1",
            full_name = "user/dummy-repo-1",
            private = false,
            owner = Owner(login = "user1", id = 101),
            description = "This is a sample repository 1",
            score = 4.5
        )

        val dummyRepo2 = GitHubRepo(
            id = 2,
            name = "dummy-repo-2",
            full_name = "user/dummy-repo-2",
            private = true,
            owner = Owner(login = "user2", id = 102),
            description = "This is a sample repository 2",
            score = 3.8
        )
        val testData: List<GitHubRepo> = listOf(dummyRepo1, dummyRepo2)
        val collectedData: MutableList<GitHubRepo> = mutableListOf()

        // Mock the repository function to return an ApiResult
        `when`(repository.getTrendingListing("", ""))
            .thenReturn(ApiResult.Success(testData))

        // Call the function and collect the result
        val result: Flow<PagingData<GitHubRepo>> = viewModel.getTrendingGithubProfiles()

        result.collect { pagingData ->
            pagingData.map { item ->
                collectedData.add(item)
            }
        }

        // Verify that the result matches your expectations
        assertEquals(testData, collectedData)
    }
}
