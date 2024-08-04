package org.chzz.market.domain.oauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login/kakao")
public class KakaoOAuth2Controller {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    String clientId;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    String redirectUri;
    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    String clientSecret;

    // 카카오 API 연결
    @GetMapping(value="")
    public String connect() {
        // 카카오톡에서 자동 로그인
        StringBuilder url = new StringBuilder();
        url.append("https://kauth.kakao.com/oauth/authorize?");
        url.append("client_id=" + clientId);
        url.append("&redirect_uri=" + redirectUri);
        url.append("&response_type=code");

        return "redirect:" + url.toString();
    }

}