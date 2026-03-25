package com.maplescouter.domain.repository

import com.maplescouter.domain.model.ApiResult
import com.maplescouter.domain.model.CharacterBasic
import com.maplescouter.domain.model.CharacterInfo

/*
    의존성 역전 원칙(Dependency Inversion Principle)에 따라 Domain, Data 모두 생성
    Domain 모듈 Repository Interface
    비즈니스 로직이 Remote에서 오는지 Local에서 모르도록 추상화된 인터페이스만 정의
    그리고 Domain 모듈은 순수 kotlin으로 작성해야 함

    어떤 데이터를 가져올 것인가에 대한 규약 정의

 */
interface MapleRepository {
    suspend fun getCharacterList(): ApiResult<List<CharacterInfo>>
    suspend fun getCharacterBasic(name: String): ApiResult<CharacterBasic>
}
