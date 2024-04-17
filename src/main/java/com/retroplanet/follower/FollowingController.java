package com.retroplanet.follower;

import com.retroplanet.feed.FeedRepository;
import com.retroplanet.feed.FeedService;
import com.retroplanet.user.SiteUser;
import com.retroplanet.user.UserRepository;
import com.retroplanet.user.UserService;
import com.retroplanet.userprofile.UserProfile;
import com.retroplanet.userprofile.UserProfileService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/{username}")
public class FollowingController {

  private final UserService userService;
  private final UserRepository userRepository;
  private final UserProfileService userProfileService;
  private Authentication authentication;

  @PostMapping("/follow")
  public String follow(@RequestParam("follower") Long follower,
      @RequestParam("followee") Long followee) {

    this.userService.follow(follower, followee);

    String username = this.userRepository.findById(followee).get().getUsername();

    return "redirect:/" + username;
  }


  @GetMapping("/follower")
  public String follower(@PathVariable String username, Model model) {

    SiteUser user = defaultAccessUser(username, model);
    defaultAccessUserProfile(username, model);

    Set<SiteUser> followers = this.userRepository.findFollowersByUsername(username);
    Set<SiteUser> following = this.userRepository.findFollowingsByUsername(username);
    model.addAttribute("followers", followers);

    return "user/follow/follower";


  }

  @GetMapping("/following")
  public String following(@PathVariable String username, Model model) {

    SiteUser user = defaultAccessUser(username, model);
    defaultAccessUserProfile(username, model);

    Set<SiteUser> followers = this.userRepository.findFollowersByUsername(username);
    Set<SiteUser> following = this.userRepository.findFollowingsByUsername(username);
    model.addAttribute("following", following);

    return "user/follow/following";


  }

  private SiteUser defaultAccessUser(String username, Model model) {
    SiteUser user = userService.getUser(username);

    authentication = SecurityContextHolder.getContext().getAuthentication();
    SiteUser logInUser = userService.getUser(authentication.getName());

    if (logInUser != null) {
      model.addAttribute("logInUser", logInUser);
    } else {
      model.addAttribute("logInUser", null);
    }

    // 조회된 사용자 정보를 모델에 담아 프로필 페이지로 전달
    model.addAttribute("user", user);

    return user;
  }


  private UserProfile defaultAccessUserProfile(String username, Model model) {
    UserProfile userProfile = userProfileService.getUserProfile(username);

    // 조회된 사용자 정보를 모델에 담아 프로필 페이지로 전달
    model.addAttribute("userprofile", userProfile);

    return userProfile;
  }
}

