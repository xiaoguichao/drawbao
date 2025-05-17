package com.windpicker.drawbao.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WxService {

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getOpenIdFromCode(String code) throws Exception {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.weixin.qq.com/sns/jscode2session")
                .queryParam("appid", appid)
                .queryParam("secret", secret)
                .queryParam("js_code", code)
                .queryParam("grant_type", "authorization_code")
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);
        JsonNode json = objectMapper.readTree(response);

        if (json.has("openid")) {
            return json.get("openid").asText();
        } else {
            throw new RuntimeException("微信登录失败：" + json.toString());
        }
    }
}
