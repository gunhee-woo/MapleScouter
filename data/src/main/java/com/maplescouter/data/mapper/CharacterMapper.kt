package com.maplescouter.data.mapper

import com.maplescouter.data.remote.dto.CharacterInfo
import com.maplescouter.domain.model.CharacterInfo as DomainCharacterInfo

fun CharacterInfo.toDomain(): DomainCharacterInfo {
    return DomainCharacterInfo(
        ocid = this.ocid,
        name = this.characterName,
        world = this.worldName,
        job = this.characterClass,
        level = this.characterLevel
    )
}
