package com.example.demo.service;

import com.alibaba.fastjson2.JSON;
import com.example.demo.model.TimeResponse;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TimeHttpClient {

    private final OkHttpClient httpClient;

    public TimeHttpClient() {
        this.httpClient = new OkHttpClient();
    }

    public boolean sendTimeData(String url, TimeResponse timeResponse) {
        try {
            String jsonData = JSON.toJSONString(timeResponse);
            
            RequestBody body = RequestBody.create(
                jsonData, 
                MediaType.parse("application/json; charset=utf-8")
            );

            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            try (Response response = httpClient.newCall(request).execute()) {
                return response.isSuccessful();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}