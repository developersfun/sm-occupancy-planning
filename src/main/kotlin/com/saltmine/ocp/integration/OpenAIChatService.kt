package com.saltmine.ocp.integration

import org.springframework.ai.azure.openai.AzureOpenAiChatModel
import org.springframework.ai.azure.openai.AzureOpenAiChatOptions
import org.springframework.ai.azure.openai.AzureOpenAiResponseFormat
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class OpenAiChatService(
    private val chatModel: AzureOpenAiChatModel
){
    open fun generateResponseForQuery(input: String): Flux<String> {
        return Flux.create { sink ->
            try {
                val response: ChatResponse = chatModel.call(
                    Prompt(
                        input,
                        AzureOpenAiChatOptions.builder()
                            .responseFormat(AzureOpenAiResponseFormat.TEXT)
                            .build()
                    )
                )
                sink.next(response.result.output.text ?: "No response")
                sink.complete()
            } catch (e: Exception) {
                sink.error(e)
            }
        }
    }
}