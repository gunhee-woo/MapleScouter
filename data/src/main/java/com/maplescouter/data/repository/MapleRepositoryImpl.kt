package com.maplescouter.data.repository

import com.maplescouter.data.mapper.toDomain
import com.maplescouter.data.remote.datasource.MapleRemoteDataSource
import com.maplescouter.domain.model.ApiResult
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
}
