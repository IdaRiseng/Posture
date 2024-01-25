package no.sporty.posture.model.manager

import no.sporty.posture.model.data.AffirmationApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AffirmationManager {
    private const val AFFIRMATION_BASE_URL = "https://www.affirmations.dev"

  suspend  fun getAffirmations(callback: (String?) -> Unit){
        val api = getApiBuilder(AFFIRMATION_BASE_URL)
      val response = api.getAffirmation()
       if (response.isSuccessful) {
           callback(response.body()?.affirmation)
       }


}
}

private fun getApiBuilder(baseUrl: String) =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AffirmationApiService::class.java)
