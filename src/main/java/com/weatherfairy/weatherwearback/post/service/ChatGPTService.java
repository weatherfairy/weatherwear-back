package com.weatherfairy.weatherwearback.post.service;

import com.google.api.gax.rpc.StatusCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGPTService {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    private static final String system_prompt = "from the given data: [id, min temperature, max temperature, weather]" +
            "find 3 most similar data from 'today weather' and return the 3 id in list.";

    @Value("${chatgpt.key}")
    String key;

    public String sendRequest(String today, String data) {

        String requestBody = "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"stream\" : false,\n" +
                "  \"messages\": [\n" +
                "    {\n" +
                "      \"role\": \"system\",\n" +
                "      \"content\": \"find 3 most similar data with 'today weather' from the given data: [id, min temperature, max temperature, weather] and response the 3 ids in list [] (no explanation)\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": \"today: " + today + " data:" +  data + "\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + key);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

        HttpStatusCode statusCode = response.getStatusCode();
        String responseBody = response.getBody();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);

        return responseBody;
    }
}
