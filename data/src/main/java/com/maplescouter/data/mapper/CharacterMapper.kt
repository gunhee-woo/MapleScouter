package com.maplescouter.data.mapper

import com.maplescouter.data.remote.dto.CharacterBasic as DtoCharacterBasic
import com.maplescouter.data.remote.dto.CharacterInfo as DtoCharacterInfo
import com.maplescouter.domain.model.CharacterBasic as DomainCharacterBasic
import com.maplescouter.domain.model.CharacterInfo as DomainCharacterInfo

fun DtoCharacterInfo.toDomain(): DomainCharacterInfo {
    return DomainCharacterInfo(
        ocid = this.ocid,
        name = this.characterName,
        world = this.worldName,
        job = this.characterClass,
        level = this.characterLevel
    )
}

fun DtoCharacterBasic.toDomain(): DomainCharacterBasic {
    return DomainCharacterBasic(
        date = this.date,
        name = this.characterName,
        worldName = this.worldName,
        gender = this.characterGender,
        className = this.characterClass,
        classLevel = this.characterClassLevel,
        level = this.characterLevel,
        exp = this.characterExp,
        expRate = this.characterExpRate,
        guildName = this.characterGuildName,
        imageUrl = this.characterImage,
        createDate = this.characterDateCreate,
        accessFlag = this.accessFlag == "true",
        liberationQuestClear = this.liberationQuestClear == "1"
    )
}
