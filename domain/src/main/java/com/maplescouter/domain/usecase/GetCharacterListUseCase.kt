package com.maplescouter.domain.usecase

import com.maplescouter.domain.model.ApiResult
import com.maplescouter.domain.model.CharacterInfo
import com.maplescouter.domain.repository.MapleRepository
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(
    private val mapleRepository: MapleRepository
) {
    suspend operator fun invoke(): ApiResult<List<CharacterInfo>> {
        return mapleRepository.getCharacterList()
    }
}
