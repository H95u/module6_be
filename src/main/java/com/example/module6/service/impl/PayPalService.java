package com.example.module6.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.UUID;

@Service
public class PayPalService {
    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.sandbox.api.base-url}")
    private String sandboxApiBaseUrl;

    public boolean withdrawFromPayPal(Double amount, String email) {
        RestTemplate restTemplate = new RestTemplate();

        // Set up headers with client ID and secret for authentication
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Create request body for payout
        String payoutItemId = "item_" + UUID.randomUUID();
        String requestBody = "{\n" +
                "  \"sender_batch_header\": {\n" +
                "    \"sender_batch_id\": \"" + payoutItemId + "\",\n" +
                "    \"email_subject\": \"You have a payment\"\n" +
                "  },\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"recipient_type\": \"EMAIL\",\n" +
                "      \"receiver\": \"" + email + "\",\n" +
                "      \"note\": \"Thank you.\",\n" +
                "      \"amount\": {\n" +
                "        \"value\": \"" + (amount / 23000) + "\",\n" +
                "        \"currency\": \"USD\"\n" +
                "      },\n" +
                "      \"sender_item_id\": \"" + payoutItemId + "\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        // Send the API request
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                sandboxApiBaseUrl + "/v1/payments/payouts",
                requestEntity,
                String.class
        );

        // Check the response status
        return responseEntity.getStatusCode().is2xxSuccessful();
    }
}
