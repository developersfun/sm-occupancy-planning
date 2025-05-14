package com.saltmine.ocp.service

import com.saltmine.ocp.AIEngine
import com.saltmine.ocp.integration.ChatFactory
import com.saltmine.ocp.integration.VergeSenseService
import com.saltmine.ocp.modal.request.SpaceRequest
import com.saltmine.ocp.modal.request.WorkspaceRequest
import com.saltmine.ocp.utils.PROMPT
import com.saltmine.ocp.utils.SPACES_JSON
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.ai.azure.openai.AzureOpenAiChatModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Ignore

@SpringBootTest
class WorkspaceServiceTest {

    @Autowired
    private lateinit var azureOpenAiChatModel: AzureOpenAiChatModel

    private val chatFactory = mock(ChatFactory::class.java)
    private val vergeService = mock(VergeSenseService::class.java)
    private val workspaceService = WorkspaceService(chatFactory, vergeService)

    @Test
    @Ignore
    fun `getWorkspace should return expected response`() {
        val spaceRequest = workspaceService.parseFindMeDeskString(PROMPT)

        `when`(vergeService.getSpaces(spaceRequest)).thenReturn(SPACES_JSON)
        `when`(chatFactory.createChat(AIEngine.AZURE_OPENAI)).thenReturn(azureOpenAiChatModel)

        val workspaceRequest = WorkspaceRequest(PROMPT, AIEngine.AZURE_OPENAI)
        val result = workspaceService.getWorkspace(workspaceRequest)

        println(result)
    }
}