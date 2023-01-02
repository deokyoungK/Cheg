package com.likelion.cheg.config.oauth;

import java.util.Map;

public class FacebookUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes;
    public FacebookUserInfo(Map<String,Object> attributes){
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "facebook_";
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
