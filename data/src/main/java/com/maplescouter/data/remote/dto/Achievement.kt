package com.maplescouter.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Achievement(
    @field:Json(name = "account_list")
    val accountList: List<AchievementAccount>
)

@JsonClass(generateAdapter = true)
data class AchievementAccount(
    @field:Json(name = "account_id") val accountId: String,
    @field:Json(name = "achievement_achieve") val achievementList: List<AchievementInfo>
)

@JsonClass(generateAdapter = true)
data class AchievementInfo(
    @field:Json(name = "achievement_name") val achievementName: String,
    @field:Json(name = "achievement_description") val achievementDescription: String
)
