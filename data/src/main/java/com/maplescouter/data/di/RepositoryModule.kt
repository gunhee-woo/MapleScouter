package com.maplescouter.data.di

import com.maplescouter.data.repository.MapleRepositoryImpl
import com.maplescouter.domain.repository.MapleRepository
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
    abstract fun bindMapleRepository(
        mapleRepositoryImpl: MapleRepositoryImpl
    ): MapleRepository
}