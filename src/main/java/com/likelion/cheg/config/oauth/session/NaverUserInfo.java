package com.likelion.cheg.config.oauth.session;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes;
    public NaverUserInfo(Map<String,Object> attributes){
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "naver_";
    }

    @Override
    public String getUsername() {
        return (String) attributes.get("id");
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }
}
