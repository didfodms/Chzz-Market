package org.chzz.market.domain.oauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.chzz.market.domain.oauth.dto.NaverUserInfoResponse;
import org.chzz.market.domain.oauth.service.NaverOAuth2UserService;
import org.chzz.market.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    private final NaverOAuth2UserService naverOAuth2UserService;

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

    // 네이버 로그인/회원가입
    @GetMapping("/code")
    public ResponseEntity<?> login(@RequestParam("code") String code) throws JsonProcessingException {
        // 1. 액세스 토큰 발급
        String accessToken = naverOAuth2UserService.getAccessToken(code);

        // 2. 회원정보 조회
        NaverUserInfoResponse naverUserInfoResponse = naverOAuth2UserService.getNaverUserInfo(accessToken);

        // 3. 회원가입
        User user = naverOAuth2UserService.join(naverUserInfoResponse);

        return ResponseEntity.ok(user);
    }
}

