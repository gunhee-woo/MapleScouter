package com.maplescouter.data.repository

import com.maplescouter.data.local.dao.CharacterDao
import com.maplescouter.data.mapper.toDomain
import com.maplescouter.data.mapper.toEntity
import com.maplescouter.data.remote.datasource.MapleRemoteDataSource
import com.maplescouter.domain.model.ApiResult
import com.maplescouter.domain.model.CharacterBasic
import com.maplescouter.domain.model.CharacterInfo
import com.maplescouter.domain.repository.MapleRepository
import javax.inject.Inject

class MapleRepositoryImpl @Inject constructor(
    private val mapleRemoteDataSource: MapleRemoteDataSource,
    private val characterDao: CharacterDao
) : MapleRepository {

    override suspend fun getCharacterList(): ApiResult<List<CharacterInfo>> {
        return try {
            // 1. 네트워크에서 최신 데이터를 가져옴
            val response = mapleRemoteDataSource.fetchCharacterList()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val characters = body.accountList.flatMap { it.characterList }
                    
                    // 2. 로컬 DB에 저장 (캐싱)
                    characterDao.insertCharacterInfoList(characters.map { it.toEntity() })
                    
                    ApiResult.Success(characters.map { it.toDomain() })
                } else {
                    ApiResult.Error("Response body is null")
                }
            } else {
                // 3. 네트워크 실패 시 로컬 DB 데이터 반환 시도 (선택 사항)
                ApiResult.Error("API call failed")
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Unknown error occurred", e)
        }
    }

    override suspend fun getCharacterBasic(ocid: String, date: String?): ApiResult<CharacterBasic> {
        return try {
            // 1. 로컬 DB에서 먼저 확인
            val localData = characterDao.getCharacterBasicById(ocid)
            
            // 2. 로컬 데이터가 있고, 너무 오래되지 않았다면(예: 1시간 이내) 바로 반환
            // 여기서는 단순하게 데이터가 있으면 반환하는 예시로 작성
            if (localData != null && !isDataExpired(localData.lastUpdated)) {
                return ApiResult.Success(localData.toDomain())
            }

            // 3. 데이터가 없거나 만료되었다면 네트워크 호출
            val response = mapleRemoteDataSource.fetchCharacterBasic(ocid, date)
            if (response.isSuccessful) {
                val dto = response.body()
                if (dto != null) {
                    // 4. 로컬 DB에 업데이트
                    characterDao.insertCharacterBasic(dto.toEntity(ocid))
                    ApiResult.Success(dto.toDomain())
                } else {
                    ApiResult.Error("Data is null")
                }
            } else {
                // 네트워크 실패 시 오래된 데이터라도 있으면 반환
                localData?.let { ApiResult.Success(it.toDomain()) } 
                    ?: ApiResult.Error("API call failed and no local data")
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Error", e)
        }
    }

    /**
     * 데이터 만료 여부 확인 (예: 1시간)
     */
    private fun isDataExpired(lastUpdated: Long): Boolean {
        val oneHourMillis = 60 * 60 * 1000L
        return System.currentTimeMillis() - lastUpdated > oneHourMillis
    }
}
