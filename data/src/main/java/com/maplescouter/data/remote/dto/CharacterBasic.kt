package com.maplescouter.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterBasic(
    @field:Json(name = "date")
    val date: String?,
    @field:Json(name = "character_name")
    val characterName: String,
    @field:Json(name = "world_name")
    val worldName: String,
    @field:Json(name = "character_gender")
    val characterGender: String,
    @field:Json(name = "character_class")
    val characterClass: String,
    @field:Json(name = "character_class_level")
    val characterClassLevel: String,
    @field:Json(name = "character_level")
    val characterLevel: Int,
    @field:Json(name = "character_exp")
    val characterExp: Long,
    @field:Json(name = "character_exp_rate")
    val characterExpRate: String,
    @field:Json(name = "character_guild_name")
    val characterGuildName: String?,
    @field:Json(name = "character_image")
    val characterImage: String,
    @field:Json(name = "character_date_create")
    val characterDateCreate: String,
    @field:Json(name = "access_flag")
    val accessFlag: String,
    @field:Json(name = "liberation_quest_clear")
    val liberationQuestClear: String
)
