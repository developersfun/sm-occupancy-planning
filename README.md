# Getting Started

This project is a Spring Boot application that integrates with various AI 
chat services, including Anthropic Claude and Azure OpenAI. 
It provides a WebSocket interface for real-time communication and a 
REST API for standard HTTP requests.
It is a part of assignment for "Saltmine"

### Reference Documentation

For further reference, please consider the following sections:

* [Anthropic Claude](https://docs.spring.io/spring-ai/reference/api/chat/anthropic-chat.html)
* [Azure OpenAI](https://docs.spring.io/spring-ai/reference/api/chat/azure-openai-chat.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.5/reference/web/servlet.html)
* [WebSocket](https://docs.spring.io/spring-boot/3.4.5/reference/messaging/websockets.html)

### Running the project

1. Clone the repository:
   ```bash
   git clone
    ```
2. Navigate to the project directory:
    ```bash
     cd <repository-name>
    ```
   
3. Docker Build and Run : 

   ```bash
     docker build -t spring-ai-chat .
     docker run -e AOKEY=<YOUR_AZURE_API_KEY> 
                -e OKEY=<YOUR_OPENAI_API_KEY>
                -e CKEY=<YOUR_CLAUDE
                -e VKEY=<YOUR_VERGE_SENSE_API_KEY>
                oc_planning
   
   ```

4. Access the application:
5. Open your REST client and use the base URL `http://localhost:8080` to access the application.
6. Use the WebSocket client to interact with the AI chat services, at : `ws://localhost:8080/chat/websocket`
   
   ```bash
   CONNECT
   accept-version:1.1,1.2
   host:localhost
   ^@
   ```
   ```bash
   SUBSCRIBE
   id:sub-0
   destination:/oc/messages
   \u0000   
   ```
   ```bash
   SEND
   destination:/app/chat

   {"prompt":"Find me an available standing desk near the marketing team on the 3rd floor for tomorrow afternoon."}
   \u0000
   ```
   
7. For WebSocket, you can use a tool like [WebSocket Test Client](https://www.websocket.org/echo.html) to test the WebSocket connection.
8. For REST API, you can use tools like Postman or curl to send HTTP requests to the endpoints provided by the application.
9. For example, to send a message to the AI chat service, you can use the following curl command:
   ```bash
   curl -X GET http://localhost:8080/api/v1/workspace \
   -H "Content-Type: application/json" \
   -d '{
   "query": "Find me an available standing desk near the marketing team on the 3rd floor for tomorrow afternoon.",
   "aiEngine": "AZURE_OPENAI"
   }'
   ```
***Note : The system is designed to work with the specific Messages only due to time constraints.***

Sample Response : 
```json

Hello! I've found a standing desk for you near the marketing team on the 3rd floor for tomorrow afternoon. You can use the space details provided in the JSON format below to proceed with your booking:

{
  "desktype": "standing",
  "location": {
    "floor_number": 3,
    "zone": "Marketing Zone",
    "team_area": "Marketing Team"
  },
  "time": {
    "datetime_range": "tomorrow afternoon"
  },
  "proximity": "near Marketing Team",
  "spaces": [
    {
      "id": "space000011",
      "name": "3rd Floor",
      "type": "floor",
      "capacity": 120,
      "parentid": "buildinghq"
    },
    {
      "id": "space000022",
      "name": "Marketing Zone",
      "type": "zone",
      "capacity": 25,
      "parentid": "space000011"
    }
  ]
}

Let me know if you need further assistance or if there's anything else I can help you with!
```
