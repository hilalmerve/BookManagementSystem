package com.example.demo.service.oauth2;

import java.util.Map;

import com.example.demo.entity.Provider;
import com.example.demo.exception.BaseException;

public class OAuth2UserDetailsFactory {
    
    public static OAuth2UserDetails getOAuth2UserDetail(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equals(Provider.google.name())) {
            return new OAuth2GoogleUser(attributes);
        
    }else {
        throw new BaseException("400", "Sorry");
    }
}
}
