package com.saltmine.ocp.response

data class AzureChatResponse (
    val results: List<ChatResult>,
    val result: ChatResult,
    val metadata: ResponseMetadata
)

data class ChatResult(
    val output: Output,
    val metadata: ResultMetadata
)

data class Output(
    val messageType: String,
    val metadata: OutputMetadata,
    val toolCalls: List<Any>, // Define a class if needed
    val media: List<Any>,     // Define a class if needed
    val text: String
)

data class OutputMetadata(
    val choiceIndex: Int,
    val finishReason: String,
    val id: String,
    val messageType: String
)

data class ResultMetadata(
    val finishReason: String,
    val contentFilters: List<Any>, // Define if needed
    val empty: Boolean
)

data class ResponseMetadata(
    val id: String,
    val model: String,
    val rateLimit: RateLimit,
    val usage: Usage,
    val promptMetadata: List<PromptMetadata>,
    val empty: Boolean
)

data class RateLimit(
    val requestsLimit: Int,
    val requestsRemaining: Int,
    val requestsReset: String,
    val tokensLimit: Int,
    val tokensRemaining: Int,
    val tokensReset: String
)

data class Usage(
    val promptTokens: Int,
    val completionTokens: Int,
    val totalTokens: Int,
    val nativeUsage: NativeUsage
)

data class NativeUsage(
    val completionTokens: Int,
    val promptTokens: Int,
    val totalTokens: Int,
    val promptTokensDetails: Any?, // Null in your case; define if needed
    val completionTokensDetails: Any?
)

data class PromptMetadata(
    val promptIndex: Int,
    val contentFilterMetadata: ContentFilterMetadata
)

data class ContentFilterMetadata(
    val sexual: FilterLevel,
    val violence: FilterLevel,
    val hate: FilterLevel,
    val selfHarm: FilterLevel,
    val profanity: Any?,
    val customBlocklists: Any?,
    val error: Any?,
    val jailbreak: Jailbreak,
    val indirectAttack: Any?
)

data class FilterLevel(
    val severity: String,
    val filtered: Boolean
)

data class Jailbreak(
    val filtered: Boolean,
    val detected: Boolean
)