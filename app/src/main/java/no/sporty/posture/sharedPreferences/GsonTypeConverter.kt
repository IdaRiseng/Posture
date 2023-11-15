package no.sporty.posture.sharedPreferences

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalDate


val GSON: Gson by lazy {
    GsonBuilder().setLenient()
        // LocalDate
        .registerTypeAdapter(LocalDate::class.java, JsonDeserializer { json: JsonElement?, _: Type?, _: JsonDeserializationContext? ->
            if (json == null) null else LocalDate.parse(json.asString)
        })
        .registerTypeAdapter(LocalDate::class.java, JsonSerializer { localDate: LocalDate, _: Type?, _: JsonSerializationContext? ->
            JsonPrimitive(localDate.toString())
        } as JsonSerializer<LocalDate>).create()
}