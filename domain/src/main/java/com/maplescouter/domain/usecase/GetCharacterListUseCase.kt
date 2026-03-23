package com.maplescouter.domain.usecase

import com.maplescouter.domain.repository.MapleRepository
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(
    private val mapleRepository: MapleRepository
) {

}