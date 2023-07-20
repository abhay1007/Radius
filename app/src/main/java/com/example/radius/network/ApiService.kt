package com.example.radius.network

import com.example.radius.model.ApiResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("iranjith4/ad-assignment/db")
    fun getFacilities(): Observable<ApiResponse>
}
