package com.retroplanet.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

  private final UserService userService;


  @GetMapping("/{username}")
  public String userProfile(@PathVariable String username, Model model) {
    // 사용자의 username을 기반으로 해당 사용자 정보를 조회
    SiteUser user = userService.getUser(username);

    if (user == null) {
      // 사용자가 없을 경우 에러 페이지 또는 적절한 처리를 수행
      System.out.println("실패");
    }

    // 조회된 사용자 정보를 모델에 담아 프로필 페이지로 전달
    model.addAttribute("user", user);
    return "user/main"; // 프로필 페이지의 템플릿 이름
  }


  @GetMapping("/{username}/profilesetting")
  public String profilesetting(@PathVariable String username, Model model) {
    // 사용자의 username을 기반으로 해당 사용자 정보를 조회
    SiteUser user = userService.getUser(username);

    if (user == null) {
      // 사용자가 없을 경우 에러 페이지 또는 적절한 처리를 수행
      System.out.println("실패");
    }

    // 조회된 사용자 정보를 모델에 담아 프로필 페이지로 전달
    model.addAttribute("user", user);
    return "user/setting/profile_setting"; // 프로필 페이지의 템플릿 이름
  }


  @GetMapping("/{username}/mainpagesetting")
  public String minapagesetting(@PathVariable String username, Model model) {
    // 사용자의 username을 기반으로 해당 사용자 정보를 조회
    SiteUser user = userService.getUser(username);

    if (user == null) {
      // 사용자가 없을 경우 에러 페이지 또는 적절한 처리를 수행
      System.out.println("실패");
    }

    // 조회된 사용자 정보를 모델에 담아 프로필 페이지로 전달
    model.addAttribute("user", user);
    return "user/setting/mainpage_setting"; // 프로필 페이지의 템플릿 이름
  }


  @GetMapping("/{username}/accountsetting")
  public String accountsetting(@PathVariable String username, Model model) {
    // 사용자의 username을 기반으로 해당 사용자 정보를 조회
    SiteUser user = userService.getUser(username);

    if (user == null) {
      // 사용자가 없을 경우 에러 페이지 또는 적절한 처리를 수행
      System.out.println("실패");
    }

    // 조회된 사용자 정보를 모델에 담아 프로필 페이지로 전달
    model.addAttribute("user", user);
    return "user/setting/account_setting"; // 프로필 페이지의 템플릿 이름
  }

}
/*@PostMapping("/profile/save")
  public String saveprofile(@Valid UserProfile userProfile, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {}
  }*//*


}*/
