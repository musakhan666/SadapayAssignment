import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.sadapay.assignment.data.remote.ApiResult
import com.sadapay.assignment.data.remote.dto.search.Owner
import com.sadapay.assignment.domain.model.search.GitHubRepo
import com.sadapay.assignment.domain.repository.MainRepository
import com.sadapay.assignment.presentation.home.HomePageScreen
import com.sadapay.assignment.presentation.home.HomeViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HomePageScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var repository: MainRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    suspend fun testHomePageScreenContent() {
        // Define your test data
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

        // Mock the repository function to return an ApiResult
        Mockito.`when`(repository.getTrendingListing("", ""))
            .thenReturn(ApiResult.Success(testData))

        // Launch the HomePageScreen with the mocked ViewModel
        composeTestRule.setContent {
            HomePageScreen(openAndPopUp = {}, viewModel = HomeViewModel(repository))
        }

        // Use the composeTestRule to perform UI tests

        // Assert that a specific text is displayed on the screen
        composeTestRule.onNodeWithText("dummy-repo-1").assertIsDisplayed()
        composeTestRule.onNodeWithText("dummy-repo-2").assertIsDisplayed()
    }
}
