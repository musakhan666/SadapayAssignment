package com.sadapay.assignment.presentation.home

import com.sadapay.assignment.domain.repository.MainRepository
import com.sadapay.assignment.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(val repository: MainRepository) : BaseViewModel() {

}