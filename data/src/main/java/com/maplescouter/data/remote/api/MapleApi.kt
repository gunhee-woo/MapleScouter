package com.maplescouter.data.remote.api

import com.maplescouter.data.remote.dto.Achievement
import com.maplescouter.data.remote.dto.CharacterBasic
import com.maplescouter.data.remote.dto.CharacterList
import retrofit2.Response
import retrofit2.http.GET

interface MapleApi {

    @GET("/maplestory/v1/character/list")
    suspend fun fetchCharacterList(): Response<CharacterList>

    @GET("/maplestory/v1/user/achievement")
    suspend fun fetchAchievement(): Response<Achievement>

    @GET("/maplestory/v1/character/basic")
    suspend fun fetchCharacterBasic(): Response<CharacterBasic>
}