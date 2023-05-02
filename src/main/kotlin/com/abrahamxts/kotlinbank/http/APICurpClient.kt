package com.abrahamxts.kotlinbank.http

import com.google.gson.*
import org.springframework.http.*
import io.github.cdimascio.dotenv.dotenv
import org.springframework.web.client.RestTemplate

import com.abrahamxts.kotlinbank.models.PersonModel

class APICurpClient {

    private val gson = Gson()
    private val dotenv = dotenv()
    private val restTemplate = RestTemplate()

    fun consultarInfoPorCurp(curp: String): PersonModel? {

        data class RequestBody(val curp: String, val accountType: String)

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.set(
            "Authorization",
            "Token ${dotenv["API_KEY"]}"
        )

        val httpEntity = HttpEntity(gson.toJson(RequestBody(curp, "PF")), headers)

        val response: ResponseEntity<String> = restTemplate.postForEntity(
            "https://sandbox.moffin.mx/api/v1/query/renapo_curp",
            httpEntity,
            String::class.java
        )

        if (response.statusCodeValue != 201) return null

        val jsonResponse = gson.fromJson(response.body, JsonObject::class.java)
        val responseData = jsonResponse.getAsJsonObject("response")

        return PersonModel
            .Builder()
            .name(responseData.get("nombre").asString)
            .paternalSurname(responseData.get("apellidoPaterno").asString)
            .maternalSurname(responseData.get("apellidoMaterno").asString)
            .curp(responseData.get("curp").asString)
            .gender(responseData.get("sexo").asString)
            .birthDate(responseData.get("fechaNacimiento").asString)
            .birthEntity(responseData.get("estadoNacimiento").asString)
            .build()
    }
}