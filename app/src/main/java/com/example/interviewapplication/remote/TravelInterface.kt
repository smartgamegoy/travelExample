package com.example.interviewapplication.remote

import com.example.interviewapplication.data.TravelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TravelInterface {

    @GET("{language}/Attractions/All?")
    suspend fun getAllTravels(
        @Path("language") language: String,
        @Query("page")page: Int
    ): Response<TravelResponse>

    @GET("{language}/Attractions/All?")
    suspend fun reload(
        @Path("language") language: String,
        @Query("page")page: Int
    ): Response<TravelResponse>
}