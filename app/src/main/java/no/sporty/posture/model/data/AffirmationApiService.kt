package no.sporty.posture.model.data

import okhttp3.Call
import okhttp3.Response
import retrofit2.http.GET

interface AffirmationApiService {
    @GET("/")
    suspend fun getAffirmation(): retrofit2.Response<AffirmationResponse>
}