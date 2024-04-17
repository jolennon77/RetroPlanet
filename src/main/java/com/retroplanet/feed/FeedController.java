package com.retroplanet.feed;

import com.retroplanet.user.SiteUser;
import com.retroplanet.user.UserService;
import com.retroplanet.userprofile.UserProfile;
import com.retroplanet.userprofile.UserProfileService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/{username}/photo")
public class FeedController {

  private final UserService userService;
  private final UserProfileService userProfileService;
  private final FeedRepository feedRepository;
  private final FeedService feedService;
  private Authentication authentication;

  @GetMapping
  public String photo(@PathVariable String username, Model model) {
    // 사용자의 username을 기반으로 해당 사용자 정보를 조회

    SiteUser user = defaultAccessUser(username, model);
    defaultAccessUserProfile(username, model);

    List<Feed> feedList = this.feedRepository.findBySiteUserOrderByCreateDateDesc(user);

    model.addAttribute("feedList", feedList);
    return "user/photo/photo"; // 프로필 페이지의 템플릿 이름
  }




  @PostMapping("/upload")
  public String feedUpload(@RequestParam("username") String username,
                            @RequestParam("content") String content,
                            MultipartFile file, Model model) throws Exception {

    this.feedService.updateFeed(username, content, file);

    return "redirect:/{username}/photo";
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/delete")
  public String feedDelete(Principal principal, @RequestParam("feedId") Long feedId) {


    Feed feed = this.feedService.getFeedById(feedId);


    if(!feed.getSiteUser().getUsername().equals(principal.getName())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한없음");
    }

    this.feedService.deleteFeedById(feedId);

    return "redirect:/{username}/photo";
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
