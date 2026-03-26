package com.maplescouter.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 캐릭터 상세 정보 Entity
 */
@Entity(tableName = "character_basic")
data class CharacterBasicEntity(
    @PrimaryKey val ocid: String,
    val date: String?,
    val characterName: String,
    val worldName: String,
    val characterGender: String,
    val characterClass: String,
    val characterClassLevel: String,
    val characterLevel: Int,
    val characterExp: Long,
    val characterExpRate: String,
    val characterGuildName: String?,
    val characterImage: String,
    val characterDateCreate: String,
    val accessFlag: String,
    val liberationQuestClear: String,
    val lastUpdated: Long = System.currentTimeMillis() // 로컬 캐싱 확인용
)
