package com.maplescouter.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterBasic(
    @Json(name = "date")
    val date: String?,
    @Json(name = "character_name")
    val characterName: String,
    @Json(name = "world_name")
    val worldName: String,
    @Json(name = "character_gender")
    val characterGender: String,
    @Json(name = "character_class")
    val characterClass: String,
    @Json(name = "character_class_level")
    val characterClassLevel: String,
    @Json(name = "character_level")
    val characterLevel: Int,
    @Json(name = "character_exp")
    val characterExp: Long,
    @Json(name = "character_exp_rate")
    val characterExpRate: String,
    @Json(name = "character_guild_name")
    val characterGuildName: String?,
    @Json(name = "character_image")
    val characterImage: String,
    @Json(name = "character_date_create")
    val characterDateCreate: String,
    @Json(name = "access_flag")
    val accessFlag: String,
    @Json(name = "liberation_quest_clear")
    val liberationQuestClear: String
)
