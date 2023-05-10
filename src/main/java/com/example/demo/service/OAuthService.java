package com.example.demo.service;

import com.alibaba.fastjson2.JSON;
import com.example.demo.model.User;
import com.example.demo.model.dto.AccessTokenDTO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class OAuthService {

    @Value("${github.oauth.client_id}")
    private String clientId;
    @Value("${github.oauth.client_secret}")
    private String clientSecret;
    @Value("${github.oauth.redirect_uri}")
    private String redirectUri;

    public static final MediaType MEDIA_TYPE = MediaType.get("application/json; charset=utf-8");
    private final static String GetAccessTokenUrl = "https://github.com/login/oauth/access_token";

    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .build();

    public String getAccessToken(String code) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO(clientId, clientSecret, code, redirectUri);
        String json = JSON.toJSONString(accessTokenDTO);

        // 请求获取access_token
        RequestBody body = RequestBody.create(json, MEDIA_TYPE);
        Request request = new Request.Builder()
                .url(GetAccessTokenUrl)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            return string.split("&")[0].split("=")[1];
        } catch (IOException e) {
            log.error("异常：{}", e);
        }

        return "";
    }

    private final static String GetUserUrl = "https://api.github.com/user";
    public User getUserInfo(String accessToken) {

        Request request = new Request.Builder()
                .url(GetUserUrl)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            return JSON.parseObject(string, User.class);
        } catch (IOException e) {
            log.error("异常：{}", e);
        }
        return null;
    }
}
