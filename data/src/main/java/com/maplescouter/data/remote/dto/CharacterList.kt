package com.maplescouter.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterList(
    @field:Json(name = "account_list")
    val accountList: List<Account>
)

@JsonClass(generateAdapter = true)
data class Account(
    @field:Json(name = "account_id") val accountId: String,
    @field:Json(name = "character_list") val characterList: List<CharacterInfo>
)

@JsonClass(generateAdapter = true)
data class CharacterInfo(
    @field:Json(name = "ocid") val ocid: String,
    @field:Json(name = "character_name") val characterName: String,
    @field:Json(name = "world_name") val worldName: String,
    @field:Json(name = "character_class") val characterClass: String,
    @field:Json(name = "character_level") val characterLevel: Int
)