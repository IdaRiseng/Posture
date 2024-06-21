package no.sporty.posture.model.data

import retrofit2.Response
import retrofit2.http.GET


interface AffirmationApiService {
    @GET("/")
    suspend fun getAffirmation(): Response<AffirmationResponse>
}