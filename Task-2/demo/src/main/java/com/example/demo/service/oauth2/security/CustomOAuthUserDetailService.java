package com.example.demo.service.oauth2.security;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.demo.entity.Provider;
import com.example.demo.entity.User;
import com.example.demo.exception.BaseException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.oauth2.OAuth2UserDetails;
import com.example.demo.service.oauth2.OAuth2UserDetailsFactory;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuthUserDetailService extends DefaultOAuth2UserService{

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);


        try {
            return checkingOAuth2User(userRequest, oAuth2User);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }



    }

    private OAuth2User checkingOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserDetails oAuth2UserDetails = OAuth2UserDetailsFactory.getOAuth2UserDetail(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(ObjectUtils.isEmpty(oAuth2UserDetails)) {
            throw new BaseException("400", "Can not found user");
        }
        Optional<User> user = userRepository.findByUsernameAndProviderId(oAuth2UserDetails.getEmail(), oAuth2UserRequest.getClientRegistration().getRegistrationId());
        User userDetail;
        if(user.isPresent()) {
            userDetail = user.get();

            if(userDetail.getProviderId().equals(Provider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new BaseException("400", "invalid sign login with registration");
            }

            userDetail = updateOAuth2UserDetail(userDetail, oAuth2UserDetails);

        }else{
            userDetail = registerNewOAuth2UserDetail(oAuth2UserRequest, oAuth2UserDetails);
        }

        return new OAuth2UserDetailsCustom(
            userDetail.getId(),
            userDetail.getUsername(),
            userDetail.getPassword(),
            userDetail.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList()));
    }

    public User registerNewOAuth2UserDetail(OAuth2UserRequest oAuth2UserRequest, OAuth2UserDetails oAuth2UserDetails) {
        User user = new User();
        user.setUsername(oAuth2UserDetails.getEmail());
        user.setProviderId(oAuth2UserRequest.getClientRegistration().getRegistrationId());
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setRoles(new HashSet<>());
        user.getRoles().add(roleRepository.findByName("USER"));
        return userRepository.save(user);

    }

    public User updateOAuth2UserDetail(User user, OAuth2UserDetails oAuth2UserDetails) {
        user.setUsername(oAuth2UserDetails.getEmail());
        return userRepository.save(user);
    }
}
