package com.saltmine.ocp.client

import com.saltmine.ocp.modal.Space
import com.saltmine.ocp.modal.request.SpaceRequest
import org.springframework.ai.azure.openai.AzureOpenAiChatModel
import org.springframework.ai.azure.openai.AzureOpenAiChatOptions
import org.springframework.ai.azure.openai.AzureOpenAiResponseFormat
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class VergeSenseClient(
    private val webClient: WebClient
){

    @Value("\${vergesense.api-key}")
    private lateinit var apiKey: String

    companion object {
        private const val VERGESENSE_BASE_URL = "https://api.vergesense.com"
    }

    fun getSpace(spaceRequest: SpaceRequest): Mono<List<Space>> {
        return webClient.get()
            .uri { uriBuilder ->
                uriBuilder.path("$VERGESENSE_BASE_URL/spaces")
                    .queryParam("building_ref_id", spaceRequest.buildingRefId)
                    .queryParam("floor_ref_id", spaceRequest.floorRefId)
                    .queryParam("space_ref_id", spaceRequest.spaceRefId)
                    .queryParam("space_type", spaceRequest.spaceType)
                    .build()
            }
            .header("vs-api-key", apiKey)
            .retrieve()
            .bodyToFlux(Space::class.java)
            .collectList()

    }

    fun getOccupancy(): Mono<String> {
        return webClient.get()
            .uri("$VERGESENSE_BASE_URL/spaces")
            .header("vs-api-key", apiKey)
            .retrieve()
            .bodyToMono(String::class.java)
    }
}