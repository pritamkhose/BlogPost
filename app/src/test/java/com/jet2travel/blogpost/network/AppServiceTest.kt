package com.jet2travel.blogpost.network

import com.jet2travel.blogpost.utils.Constants
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class AppServiceTest {

    private lateinit var service: ApiInterface

    @Before
    fun createService() {
        // call the api
        service = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    @Test
    fun `App Api correct 1`() = runBlocking {
        val response = service.getBlogLists(1, Constants.PAGE_LIMIT)
        // verify the response is OK
        assertNotEquals(response, null)
        assertNotEquals(response, "")
    }

    @Test
    fun `App Api correct 2`() = runBlocking {
        val response = service.getBlogLists(5, 10)
        // verify the response is OK
        assertNotEquals(response, null)
        assertNotEquals(response, "")
    }


}