package com.saltmine.ocp.controller

import com.saltmine.ocp.integration.OpenAiChatService
import org.springframework.ai.azure.openai.AzureOpenAiChatModel
import org.springframework.ai.azure.openai.AzureOpenAiChatOptions
import org.springframework.ai.azure.openai.AzureOpenAiResponseFormat
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


@Controller
class ChatController(
    private val openAiChatService: OpenAiChatService
) {

    @MessageMapping("/chat")
    @SendTo("/oc/responses")
    open fun chat(input: String): Flux<String> {
        return openAiChatService.generateResponseForQuery(input);
    }
}