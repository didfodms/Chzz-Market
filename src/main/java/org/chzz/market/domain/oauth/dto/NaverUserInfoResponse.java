package org.chzz.market.domain.oauth.dto;

import lombok.NoArgsConstructor;
import org.chzz.market.domain.user.entity.User.ProviderType;

import java.util.Map;

@NoArgsConstructor
public class NaverUserInfoResponse {
    private Map<String, Object> attributes;

    public NaverUserInfoResponse(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getProviderId() {
        return String.valueOf(attributes.get("id"));
    }

    public ProviderType getProviderType() {
        return ProviderType.NAVER;
    }

    public String getEmail() {
        return String.valueOf(attributes.get("email"));
    }

    public String getNickname() {
        return String.valueOf(attributes.get("nickname"));
    }

}
