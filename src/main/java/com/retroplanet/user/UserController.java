package com.retroplanet.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

  private final UserService userService;



  @GetMapping("/signup")
  public String signup(UserCreateForm userCreateForm) {
    return "user/signup_form";
  }

  @PostMapping("/signup")
  public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "user/signup_form";
    }
    if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
      bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
      return "user/signup_form";
    }

    try {

      userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
    } catch (DataIntegrityViolationException e){
      e.printStackTrace();
      bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
      return "user/signup_form";
    } catch (Exception e){
      e.printStackTrace();
      bindingResult.reject("signupFailed", e.getMessage());
      return "user/signup_form";
    }

    return "redirect:/";

  }

  @GetMapping("/login")
  public String login(){
    return "user/login_form";
  }


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
    return "user/content"; // 프로필 페이지의 템플릿 이름
  }
}