package com.retroplanet.OAuth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retroplanet.user.SiteUser;
import com.retroplanet.user.UserRepository;
import com.retroplanet.userprofile.UserProfile;
import com.retroplanet.userprofile.UserProfileRepository;
import com.retroplanet.userprofile.UserProfileService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {


  private final UserRepository userRepository;
  private final UserProfileService userProfileService;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    String oauthClientName = userRequest.getClientRegistration().getClientName();
    try {
      System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAuthorities()));
    } catch (Exception e) {
      e.printStackTrace();
    }

    SiteUser siteUser = null;
    String username = null;
    String email = "email@email.com";
    UserProfile userProfile =null;
//
    if (oauthClientName.equals("kakao")) {

      //TODO 카카오 로그인 구현
    }

    if (oauthClientName.equals("naver")) {
      Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("response");

      //유저 이메일이 있는경우 로그인
      email = responseMap.get("email");
      SiteUser checkUser = this.userRepository.findByEmail(email);


      if (checkUser != null) {
        return new CustomOAuth2User(checkUser.getUsername());
      }

      //sns유저가 없으면 가입후 페이지 이동
      username = "N_" + responseMap.get("id").substring(0, 14);
      siteUser = new SiteUser(username, email);
      this.userRepository.save(siteUser);
      userProfile = this.userProfileService.saveUserProfile(siteUser);
    }

    return new CustomOAuth2User(username);
  }
}
