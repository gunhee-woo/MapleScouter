package com.maplescouter.data.remote.datasource

import com.maplescouter.data.remote.dto.Achievement
import com.maplescouter.data.remote.dto.CharacterList
import retrofit2.Response

/*
    DataSource
    데이터 계층의 최하단에서 데이터 소스(Network, DB)와 직접 통신하는 역할
    RemoteDataSource, LocalDataSource
    1. 유연성
    추후 Retrofit 대신 다른 라이브러리(Ktor) 바꾸더라도 인터페이스를 상속받은 구현체만 만들면 됨
    다른 상위 코드(Repository)는 수정할 필요가 없어짐
    2. 테스트
    Repository를 테스트할 때 실제 네트워크 호출 대신 Mock DataSource를 주입하기 매우 쉬워짐
 */
interface MapleRemoteDataSource {
    suspend fun fetchCharacterList(): Response<CharacterList>
    suspend fun fetchAchievement(): Response<Achievement>
}