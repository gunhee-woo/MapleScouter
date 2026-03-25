package com.maplescouter.domain.usecase

import com.maplescouter.domain.model.ApiResult
import com.maplescouter.domain.model.CharacterBasic
import com.maplescouter.domain.model.CharacterInfo
import com.maplescouter.domain.repository.MapleRepository
import javax.inject.Inject

class GetCharacterBasicUseCase @Inject constructor(
    private val mapleRepository: MapleRepository
) {
    suspend operator fun invoke(name: String): ApiResult<CharacterBasic> {
        return mapleRepository.getCharacterBasic(name)
    }
}
