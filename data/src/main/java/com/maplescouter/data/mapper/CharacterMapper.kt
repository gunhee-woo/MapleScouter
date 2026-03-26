package com.maplescouter.data.mapper

import com.maplescouter.data.local.entity.CharacterBasicEntity
import com.maplescouter.data.local.entity.CharacterInfoEntity
import com.maplescouter.data.remote.dto.CharacterBasic as DtoCharacterBasic
import com.maplescouter.data.remote.dto.CharacterInfo as DtoCharacterInfo
import com.maplescouter.domain.model.CharacterBasic as DomainCharacterBasic
import com.maplescouter.domain.model.CharacterInfo as DomainCharacterInfo

// DTO -> Domain
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

// DTO -> Entity
fun DtoCharacterInfo.toEntity(): CharacterInfoEntity {
    return CharacterInfoEntity(
        ocid = this.ocid,
        characterName = this.characterName,
        worldName = this.worldName,
        characterClass = this.characterClass,
        characterLevel = this.characterLevel
    )
}

fun DtoCharacterBasic.toEntity(ocid: String): CharacterBasicEntity {
    return CharacterBasicEntity(
        ocid = ocid,
        date = this.date,
        characterName = this.characterName,
        worldName = this.worldName,
        characterGender = this.characterGender,
        characterClass = this.characterClass,
        characterClassLevel = this.characterClassLevel,
        characterLevel = this.characterLevel,
        characterExp = this.characterExp,
        characterExpRate = this.characterExpRate,
        characterGuildName = this.characterGuildName,
        characterImage = this.characterImage,
        characterDateCreate = this.characterDateCreate,
        accessFlag = this.accessFlag,
        liberationQuestClear = this.liberationQuestClear
    )
}

// Entity -> Domain
fun CharacterInfoEntity.toDomain(): DomainCharacterInfo {
    return DomainCharacterInfo(
        ocid = this.ocid,
        name = this.characterName,
        world = this.worldName,
        job = this.characterClass,
        level = this.characterLevel
    )
}

fun CharacterBasicEntity.toDomain(): DomainCharacterBasic {
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
