package com.maplescouter.data.remote.datasource

import com.maplescouter.data.remote.api.MapleApi
import com.maplescouter.data.remote.dto.Achievement
import com.maplescouter.data.remote.dto.CharacterList
import retrofit2.Response
import javax.inject.Inject

class MapleRemoteDataSourceImpl @Inject constructor(
    private val mapleApi: MapleApi
): MapleRemoteDataSource {
    override suspend fun fetchCharacterList(): Response<CharacterList> {
        return mapleApi.fetchCharacterList()
    }

    override suspend fun fetchAchievement(): Response<Achievement> {
        return mapleApi.fetchAchievement()
    }
}