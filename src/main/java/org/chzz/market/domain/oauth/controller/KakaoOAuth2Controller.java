package org.chzz.market.domain.oauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.chzz.market.domain.oauth.dto.KakaoUserInfoResponse;
import org.chzz.market.domain.oauth.service.KakaoOAuth2UserService;
import org.chzz.market.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    private final KakaoOAuth2UserService kakaoOAuth2UserService;

    // 카카오 API 연결
    @GetMapping(value="")
    public String connect() {

        StringBuilder url = new StringBuilder();
        url.append("https://kauth.kakao.com/oauth/authorize?");
        url.append("client_id=" + clientId);
        url.append("&redirect_uri=" + redirectUri);
        url.append("&response_type=code");

        return "redirect:" + url.toString();
    }

    // 카카오 로그인 + 회원가입
    @GetMapping("/code")
    public ResponseEntity<?> login(@RequestParam("code") String code) throws JsonProcessingException {
        // 1. 액세스 토큰 발급
        String accessToken = kakaoOAuth2UserService.getAccessToken(code);

        // 2. 회원정보 조회
        KakaoUserInfoResponse kakaoUserInfoResponse = kakaoOAuth2UserService.getKakaoUserInfo(accessToken);

        // 3. 회원가입
        User user = kakaoOAuth2UserService.join(kakaoUserInfoResponse);

        return ResponseEntity.ok(user);
    }
}