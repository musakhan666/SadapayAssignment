package com.sadapay.assignment.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sadapay.assignment.presentation.shimmer.ShimmerAnimation
import com.sadapay.assignment.R
import com.sadapay.assignment.domain.model.search.GitHubRepo
import com.sadapay.assignment.presentation.retry.retryScreen
import com.sadapay.assignment.ui.cards.GitHubRepoCard
import com.sadapay.assignment.ui.text.MediumTitle

@Composable
fun HomePageScreen(
    openAndPopUp: (String) -> Unit = {}, viewModel: HomeViewModel = hiltViewModel()
) {
    val isRefreshing = remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing.value)
    val trendingGithubItems = viewModel.getTrendingGithubProfiles().collectAsLazyPagingItems()
    val context = LocalContext.current

    fun LazyListScope.githubItems(items: LazyPagingItems<GitHubRepo>) {
        isRefreshing.value = false
        if (!items.itemSnapshotList.items.isNullOrEmpty()) {
            items(
                count = items.itemCount,
                key = items.itemKey(),
                contentType = items.itemContentType()
            ) { index ->
                val item = items[index]
                item?.let {
                    GitHubRepoCard(it) {}
                }
            }
        }
    }

    SwipeRefresh(
        state = swipeRefreshState, onRefresh = {
            isRefreshing.value = true
            trendingGithubItems.refresh()
        }, modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            topBar = {
                topBar()

            }
        ) { paddings ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddings)
                    .fillMaxSize()
            ) {
                when (trendingGithubItems.loadState.refresh) {
                    is LoadState.Loading -> { item { ShimmerAnimation() } }
                    is LoadState.Error -> {
                        item { retryScreen { trendingGithubItems.refresh() } }
                        Toast.makeText(context, "Error: ${(trendingGithubItems.loadState.refresh as LoadState.Error).error.message}", Toast.LENGTH_SHORT).show()

                    }
                    else -> githubItems(trendingGithubItems)
                }
            }
        }
    }


}

@Composable
private fun topBar(
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth().background(
                Color.White, RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
            )
        ) {
            Row(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 20.dp).fillMaxWidth()
            ) {
                MediumTitle(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp).fillMaxWidth().weight(1f)
                        .align(Alignment.CenterVertically),
                    text = stringResource(R.string.top_bar_title),
                    fontSize = 24.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                Icon(
                    painter = painterResource(R.drawable.more_ic_24dp),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    contentDescription = "moreIcon",
                    tint = Color.Black
                )

            }
            Spacer(modifier = Modifier.height(15.dp))
            Divider(modifier = Modifier.height(1.dp).fillMaxWidth().background(color = Color.Gray))
        }


    }

}