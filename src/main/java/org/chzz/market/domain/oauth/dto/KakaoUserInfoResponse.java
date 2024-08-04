package org.chzz.market.domain.oauth.dto;

import lombok.NoArgsConstructor;
import org.chzz.market.domain.user.entity.User.ProviderType;

import java.util.Map;

@NoArgsConstructor
public class KakaoUserInfoResponse {

    private String id;
    private Map<String, Object> kakaoAccount;
    private Map<String, Object> properties;

    public KakaoUserInfoResponse(Map<String, Object> attributes, Map<String, Object> properties, String id) {
        this.kakaoAccount = attributes;
        this.properties = properties;
        this.id = id;
    }

    public String getProviderId() {
        return id;
    }

    public ProviderType getProviderType() {
        return ProviderType.KAKAO;
    }

    public String getEmail() {
        return String.valueOf(kakaoAccount.get("email"));
    }

    public String getNickname() {
        return String.valueOf(properties.get("nickname"));
    }
}
