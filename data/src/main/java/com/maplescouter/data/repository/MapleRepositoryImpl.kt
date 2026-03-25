package com.maplescouter.data.repository

import com.maplescouter.data.mapper.toDomain
import com.maplescouter.data.remote.datasource.MapleRemoteDataSource
import com.maplescouter.domain.model.ApiResult
import com.maplescouter.domain.model.CharacterBasic
import com.maplescouter.domain.model.CharacterInfo
import com.maplescouter.domain.repository.MapleRepository
import javax.inject.Inject

class MapleRepositoryImpl @Inject constructor(
    private val mapleRemoteDataSource: MapleRemoteDataSource
) : MapleRepository {

    override suspend fun getCharacterList(): ApiResult<List<CharacterInfo>> {
        return try {
            val response = mapleRemoteDataSource.fetchCharacterList()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val domainList = body.accountList.flatMap { account ->
                        account.characterList.map { it.toDomain() }
                    }
                    ApiResult.Success(domainList)
                } else {
                    ApiResult.Error("Response body is null")
                }
            } else {
                ApiResult.Error("API call failed with code: ${response.code()}")
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Unknown error occurred", e)
        }
    }

    override suspend fun getCharacterBasic(name: String): ApiResult<CharacterBasic> {
        return try {
            val response = mapleRemoteDataSource.fetchCharacterBasic() // 임시로 전체 리스트에서 찾음 (추후 개별 API로 변경 가능)
            if (response.isSuccessful) {
                val character = response.body()?.accountList
                    ?.flatMap { it.characterList }
                    ?.find { it.ocid == ocid }
                    ?.toDomain()

                if (character != null) {
                    ApiResult.Success(character)
                } else {
                    ApiResult.Error("Character not found")
                }
            } else {
                ApiResult.Error("API call failed")
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Error", e)
        }
    }
}
