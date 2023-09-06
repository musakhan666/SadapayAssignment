package com.sadapay.assignment.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val trendingGithubItems = viewModel.getTrendingGithubs().collectAsLazyPagingItems()
    val showLoadingDialog = remember { mutableStateOf(false) }
    val showRetry = remember { mutableStateOf(false) }


    fun showLoading() {
        showLoadingDialog.value = true
    }

    fun hideLoading() {
        showLoadingDialog.value = false
        isRefreshing.value = false
        showRetry.value = false

    }

    fun refresh() {
        trendingGithubItems.refresh()
        showLoading()
        showRetry.value = false
    }

    fun LazyListScope.githubItems(items: LazyPagingItems<GitHubRepo>) {
        hideLoading()
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
            refresh()
        }, modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.navigationBarsPadding().fillMaxSize().background(Color.White)
        ) {
            item {
                Column {
                    TopBar()
                    Spacer(Modifier.height(20.dp))
                }

            }
            when {
                !showLoadingDialog.value && !showRetry.value -> {
                    when (trendingGithubItems.loadState.refresh) {
                        is LoadState.Loading -> {}
                        is LoadState.Error -> {
                            hideLoading()
                            showRetry.value = true
                        }
                        else -> githubItems(trendingGithubItems)
                    }
                }
                showLoadingDialog.value -> item { ShimmerAnimation() }
                showRetry.value -> item { retryScreen { refresh() } }
            }
        }
    }


}

@Composable
fun TopBar(
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