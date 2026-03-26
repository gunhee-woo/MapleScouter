package com.maplescouter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maplescouter.data.local.entity.CharacterBasicEntity
import com.maplescouter.data.local.entity.CharacterInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    // 캐릭터 목록 정보 관련
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterInfoList(characters: List<CharacterInfoEntity>)

    @Query("SELECT * FROM character_info")
    fun getAllCharacterInfo(): Flow<List<CharacterInfoEntity>>

    @Query("SELECT * FROM character_info WHERE ocid = :ocid")
    suspend fun getCharacterInfoById(ocid: String): CharacterInfoEntity?


    // 캐릭터 상세 정보 관련
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterBasic(character: CharacterBasicEntity)

    @Query("SELECT * FROM character_basic WHERE ocid = :ocid")
    suspend fun getCharacterBasicById(ocid: String): CharacterBasicEntity?

    @Query("DELETE FROM character_basic WHERE ocid = :ocid")
    suspend fun deleteCharacterBasic(ocid: String)
}
