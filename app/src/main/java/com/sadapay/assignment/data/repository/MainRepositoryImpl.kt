package com.sadapay.assignment.data.repository

import com.sadapay.assignment.data.local.AppDatabase
import com.sadapay.assignment.data.remote.RemoteApi
import com.sadapay.assignment.domain.repository.MainRepository
import com.sadapay.assignment.utils.ResourceProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val remoteApi: RemoteApi,
    private val database: AppDatabase,
    private val resourceProvider: ResourceProvider,
):MainRepository {

    override suspend fun fetchData() {

    }

}