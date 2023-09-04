package com.sadapay.assignment.presentation.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomePageScreen(
    openAndPopUp: (String) -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    Text(text = "Hello test!")

}