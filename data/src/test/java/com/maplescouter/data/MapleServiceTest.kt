package com.maplescouter.data

import com.maplescouter.data.remote.api.MapleApi
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Before
import org.junit.Test

class MapleServiceTest: ApiAbstract<MapleApi>() {

    private lateinit var mockService: MapleApi
    private lateinit var service: MapleApi

    @Before
    fun initService() {
        mockService = createMockWebService(MapleApi::class.java)
        service = createService(MapleApi::class.java)
    }


    @Throws(IOException::class)
    @Test
    fun fetchMockMapleCharacterListTest() = runTest {
        enqueueResponse("characterList.json")
        val response = mockService.fetchCharacterList()
        if(response.isSuccessful) {
            val responseBody = response.body()
            println("API Response Body: $responseBody")
            assert(responseBody != null)
            assert(responseBody!!.accountList.isNotEmpty())
            assert(responseBody.accountList[0].characterList.isNotEmpty())
        }

    }

    @Test
    fun fetchMapleApiTest() = runTest {
        val response = service.fetchCharacterList()
        if(response.isSuccessful) {
            val responseBody = response.body()
            println("API Response Body: $responseBody")
            assert(responseBody != null)
            assert(responseBody!!.accountList.isNotEmpty())
            assert(responseBody.accountList[0].characterList.isNotEmpty())
        } else {
            println("Error Body: ${response.errorBody()?.string()}")
        }

    }
}