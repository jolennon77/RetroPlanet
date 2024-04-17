package com.retroplanet.user;

import com.retroplanet.userprofile.UserProfile;
import com.retroplanet.userprofile.UserProfileService;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/{username}")
public class UserController {


  private final UserService userService;
  private final UserProfileService userProfileService;
  private Authentication authentication;

  @GetMapping
  public String userProfile(@PathVariable String username, Model model)
      throws UnsupportedEncodingException {

    UserProfile userProfile = defaultAccess(username, model);

    String youtubeURL = userProfile.getYouTubeURL();
    String encodedYoutubeURL = "https://www.youtube.com/embed/" + youtubeURL;
    model.addAttribute("encodedYoutubeURL", encodedYoutubeURL);

    return "user/main"; // 프로필 페이지의 템플릿 이름
  }


  @GetMapping("/profilesetting")
  public String profilesetting(@PathVariable String username, Model model) {
    // 사용자의 username을 기반으로 해당 사용자 정보를 조회

    defaultAccess(username, model);
    return "user/setting/profile_setting"; // 프로필 페이지의 템플릿 이름
  }



  @GetMapping("/mainpagesetting")
  public String minapagesetting(@PathVariable String username, Model model) {
    // 사용자의 username을 기반으로 해당 사용자 정보를 조회
    defaultAccess(username, model);
    return "user/setting/mainpage_setting"; // 프로필 페이지의 템플릿 이름
  }


  @GetMapping("/accountsetting")
  public String accountsetting(@PathVariable String username, Model model) {
    // 사용자의 username을 기반으로 해당 사용자 정보를 조회
    defaultAccess(username, model);
    return "user/setting/account_setting"; // 프로필 페이지의 템플릿 이름
  }





//기본 액세스 공용 메소드

  private UserProfile defaultAccess(String username, Model model) {
    SiteUser user = userService.getUser(username);
    UserProfile userProfile = userProfileService.getUserProfile(username);

    authentication = SecurityContextHolder.getContext().getAuthentication();
    SiteUser logInUser = userService.getUser(authentication.getName());

    if (logInUser != null) {
      model.addAttribute("logInUser", logInUser);
    } else {
      model.addAttribute("logInUser", null);
    }

    // 조회된 사용자 정보를 모델에 담아 프로필 페이지로 전달
    model.addAttribute("user", user);
    model.addAttribute("userprofile", userProfile);

    return userProfile;
  }
}