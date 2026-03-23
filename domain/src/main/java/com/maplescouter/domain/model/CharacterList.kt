package com.maplescouter.domain.model

data class CharacterList(
    val accountList: List<AccountInfo>
)

data class AccountInfo(
    val accountId: String,
    val characterList: List<CharacterInfo>
)

data class CharacterInfo(
    val ocid: String,
    val name: String,
    val world: String,
    val job: String,
    val level: Int
)
