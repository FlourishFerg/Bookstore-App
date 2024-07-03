package com.example.bookstoreapp;

import okhttp3.*;

import java.io.IOException;

public class PaymentProcessor {
    public static final String PAYSTACK_SECRET_KEY = "sk_live_9b537d9d44e13978d9753e950b3d2fae7cb6d171";

    public String createCharge(String email, long amount, String reference) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String jsonPayload = "{\"email\":\"" + email + "\",\"amount\":" + amount + ",\"reference\":\"" + reference + "\"}";
        RequestBody body = RequestBody.create(mediaType, jsonPayload);
        Request request = new Request.Builder()
                .url("https://api.paystack.co/transaction/initialize")
                .post(body)
                .addHeader("Authorization", "Bearer " + PAYSTACK_SECRET_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        System.out.println("Request Payload: " + jsonPayload); // Debugging: Print request payload

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorResponse = response.body().string();
                System.out.println("Error Response: " + errorResponse); // Debugging: Print error response
                throw new IOException("Unexpected code " + response + " - " + errorResponse);
            }

            String responseBody = response.body().string();
            System.out.println("Create Charge Response: " + responseBody);
            return responseBody;
        }
    }

    public String verifyPayment(String reference) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.paystack.co/transaction/verify/" + reference)
                .get()
                .addHeader("Authorization", "Bearer " + PAYSTACK_SECRET_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorResponse = response.body().string();
                System.out.println("Error Response: " + errorResponse);
                throw new IOException("Unexpected code " + response + " - " + errorResponse);
            }

            String responseBody = response.body().string();
            System.out.println("Verify Payment Response: " + responseBody);
            return responseBody;
        }
    }

}
