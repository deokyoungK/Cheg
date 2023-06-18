package com.likelion.cheg.config.oauth.session;


public interface OAuth2UserInfo {
    String getProvider();
    String getUsername();
    String getPassword();
    String getEmail();
    String getName();
}
