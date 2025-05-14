package com.saltmine.ocp.service

import com.saltmine.ocp.integration.ChatFactory
import com.saltmine.ocp.integration.VergeSenseService
import com.saltmine.ocp.modal.request.SpaceRequest
import com.saltmine.ocp.modal.request.WorkspaceRequest
import com.saltmine.ocp.utils.PROMPT_FOR_RESPONSE
import com.saltmine.ocp.utils.PROMPT_FOR_RESPONSE_PREFIX
import org.springframework.ai.azure.openai.AzureOpenAiChatOptions
import org.springframework.ai.azure.openai.AzureOpenAiResponseFormat
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.stereotype.Component

@Component
class WorkspaceService(
    private val chatFactory: ChatFactory,
    private val vergeService: VergeSenseService
) {

    fun getWorkspace(workspaceRequest: WorkspaceRequest): String? {

        val response :String = vergeService.getSpaces(parseFindMeDeskString(workspaceRequest.query))

        var input = PROMPT_FOR_RESPONSE_PREFIX +
                "\n" + workspaceRequest.query + "\n" +
                PROMPT_FOR_RESPONSE +
                "\n" + response + "\n"

        var chatModel = chatFactory.createChat(workspaceRequest.aiEngine)

        return  chatModel.call(
            Prompt(
                input.replace(Regex("[^a-zA-Z0-9 .,?!]"), "").take(1000),
                AzureOpenAiChatOptions.builder()
                    .responseFormat(AzureOpenAiResponseFormat.TEXT)
                    .build()
            )
        ).result.output.text
    }

    fun parseFindMeDeskString(input: String): SpaceRequest {
        val regex = Regex("Find me an (\\w+) (\\w+) desk near the (\\w+) team on the (\\w+) floor for (\\w+) (\\w+).")
        val matchResult = regex.find(input)

        return if (matchResult != null) {
            val (deskStatus, deskType, zone, floor, time, equipment) = matchResult.destructured
            SpaceRequest(
                buildingRefId = zone, // Assuming `zone` maps to `buildingRefId`
                floorRefId = floor,
                spaceRefId = "$deskStatus $deskType", // Combining desk status and type
                spaceType = equipment // Assuming `equipment` maps to `spaceType`
            )
        } else {
            throw IllegalArgumentException("Input string does not match the expected format.")
        }
    }
}