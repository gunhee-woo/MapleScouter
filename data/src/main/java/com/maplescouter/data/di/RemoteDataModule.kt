package com.maplescouter.data.di

import com.maplescouter.data.remote.api.MapleApi
import com.maplescouter.data.remote.datasource.MapleRemoteDataSource
import com.maplescouter.data.remote.datasource.MapleRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataModule {

    /*
        @Binds 인터페이스와 구현체를 연결 시 사용
        컴파일 효율성 좋음, 코드 간결 함
        내가 직접 만든 클래스의 인터페이스 연결 시 사용

        @Provides
        외부 라이브러리 객체나 특수한 로직 생성 시 사용
        메서드 바디에 객체 생성을 위한 로직이 필요 시 사용
        다른 사람이 만든 객체 가져올 때 사용

     */
    @Binds
    @Singleton
    abstract fun bindMapleRemoteDataSource(
        mapleRemoteDataSourceImpl: MapleRemoteDataSourceImpl
    ): MapleRemoteDataSource

    companion object {
        @Provides
        @Singleton
        fun provideMapleApi(retrofit: Retrofit): MapleApi {
            return retrofit.create(MapleApi::class.java)
        }
    }
}