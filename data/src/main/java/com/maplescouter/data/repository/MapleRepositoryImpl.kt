package com.maplescouter.data.repository

import com.maplescouter.data.local.MapleLocalDataSource
import com.maplescouter.data.mapper.CharacterMapper
import com.maplescouter.data.remote.datasource.MapleRemoteDataSource
import com.maplescouter.domain.model.ApiResult
import com.maplescouter.domain.model.CharacterInfo
import com.maplescouter.domain.repository.MapleRepository
import javax.inject.Inject

/*
    도메인에서 정의한 인터페이스를 실제로 구현
    DataSource 등을 호출하여 실제 데이터 처리 및 도메인 모델로 변환(Mapping)
 */
class MapleRepositoryImpl @Inject constructor(
    private val remoteDataSource: MapleRemoteDataSource,
    private val localDataSource: MapleLocalDataSource,
    private val characterMapper: CharacterMapper // Domain Model로 변환
): MapleRepository {
    override suspend fun getCharacterList(): ApiResult<List<CharacterInfo>> {
        TODO("Not yet implemented")
    }

}