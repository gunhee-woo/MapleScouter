package com.maplescouter.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_info")
data class CharacterInfoEntity(
    @PrimaryKey val ocid: String,
    val characterName: String,
    val worldName: String,
    val characterClass: String,
    val characterLevel: Int
)
