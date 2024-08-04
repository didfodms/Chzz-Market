package org.chzz.market.domain.oauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login/naver")
public class NaverOAuth2Controller {
    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    String clientId;
    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    String redirectUri;
    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    String clientSecret;

    // 네이버 API 연결
    @GetMapping(value="")
    public String connect() {
        StringBuilder url = new StringBuilder();
        url.append("https://nid.naver.com/oauth2.0/authorize?");
        url.append("client_id=" + clientId);
        url.append("&redirect_uri=" + redirectUri);
        url.append("&response_type=code");

        return "redirect:" + url.toString();
    }
}

