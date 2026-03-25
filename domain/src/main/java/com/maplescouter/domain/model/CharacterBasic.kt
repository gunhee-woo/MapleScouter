package com.maplescouter.domain.model

data class CharacterBasic(
    val date: String?,
    val name: String,
    val worldName: String,
    val gender: String,
    val className: String,
    val classLevel: String,
    val level: Int,
    val exp: Long,
    val expRate: String,
    val guildName: String?,
    val imageUrl: String,
    val createDate: String,
    val accessFlag: Boolean,
    val liberationQuestClear: Boolean
)
