package com.saltmine.ocp.integration

import com.saltmine.ocp.AIEngine
import org.springframework.ai.azure.openai.AzureOpenAiChatModel
import org.springframework.stereotype.Component


@Component
class ChatFactory(
    private val azureOpenAiChatModel: AzureOpenAiChatModel
) {

    //TODO : Change the return type to the actual chat model types
    fun createChat(aiEngine: AIEngine): AzureOpenAiChatModel {
        return when (aiEngine) {
//            AIEngine.CLAUDE -> {
//                // Create and return Claude chat instance
//            }
//            AIEngine.OPENAI -> {
//                // Create and return OpenAI chat instance
//                "OpenAI Chat Instance"
//            }
            AIEngine.AZURE_OPENAI -> {
                // Return Azure OpenAI chat instance
                azureOpenAiChatModel
            }
            else -> {
                throw IllegalArgumentException("Unsupported AI engine: $aiEngine")
            }
        }
    }
}