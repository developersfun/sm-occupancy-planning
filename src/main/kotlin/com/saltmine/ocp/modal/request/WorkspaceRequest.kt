package com.saltmine.ocp.modal.request

import com.saltmine.ocp.AIEngine
import java.time.temporal.TemporalQuery

data class WorkspaceRequest(
    val query: String,
    val aiEngine: AIEngine = AIEngine.AZURE_OPENAI
)
