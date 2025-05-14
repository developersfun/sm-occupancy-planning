package com.saltmine.ocp.controller

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.messaging.converter.StringMessageConverter
import org.springframework.messaging.simp.stomp.StompHeaders
import org.springframework.messaging.simp.stomp.StompSession
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.messaging.WebSocketStompClient
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.TimeUnit
import kotlin.test.Ignore
import kotlin.test.assertEquals

class ChatControllerTest {
    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    @Ignore
    fun `test WebSocket chat endpoint`() {
        val messageQueue = ArrayBlockingQueue<String>(1)

        // Set up WebSocket client
        val stompClient = WebSocketStompClient(StandardWebSocketClient())
        stompClient.messageConverter = StringMessageConverter()

        val session: StompSession = stompClient
            .connectAsync("ws://localhost:8080/chat", object : StompSessionHandlerAdapter() {})
            .get(10, TimeUnit.SECONDS)

        // Subscribe to the topic
        session.subscribe("/oc/responses", object : StompSessionHandlerAdapter() {
            override fun handleFrame(headers: StompHeaders, payload: Any?) {
                messageQueue.offer(payload as String)
            }
        })

        // Send a message to the WebSocket endpoint
        session.send("/ocp/chat", "Generate the names of 5 famous pirates.")

        // Assert the response
        val response = messageQueue.poll(5, TimeUnit.SECONDS)
        assertEquals("Blackbeard, Captain Kidd, Anne Bonny, Calico Jack, Bartholomew Roberts", response)
    }
}