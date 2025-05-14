package com.saltmine.ocp.integration

import com.saltmine.ocp.client.VergeSenseClient
import com.saltmine.ocp.modal.Space
import com.saltmine.ocp.modal.request.SpaceRequest
import org.springframework.ai.azure.openai.AzureOpenAiChatModel
import org.springframework.ai.azure.openai.AzureOpenAiChatOptions
import org.springframework.ai.azure.openai.AzureOpenAiResponseFormat
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class VergeSenseService(
    private val vergeSenseClient: VergeSenseClient
){

    fun getSpaces(spaceRequest: SpaceRequest): String {
        val spaces: Mono<List<Space>> = vergeSenseClient.getSpace(spaceRequest)
        return spaces.block()?.joinToString(separator = "\n") { it.toString() }
            ?: "No spaces found for the given request."
    }

}