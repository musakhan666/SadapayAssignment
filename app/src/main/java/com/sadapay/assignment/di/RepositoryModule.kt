package com.sadapay.assignment.di


import com.sadapay.assignment.data.repository.MainRepositoryImpl
import com.sadapay.assignment.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMainRepository(
        repositoryImpl: MainRepositoryImpl
    ): MainRepository
}