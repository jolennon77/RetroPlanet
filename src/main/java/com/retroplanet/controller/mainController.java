package com.retroplanet.controller;

import com.retroplanet.user.UserCreateForm;
import com.retroplanet.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Getter
@Setter
@RequiredArgsConstructor
public class mainController {
  private final UserService userService;

  @GetMapping("/")
  public String main() {
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String loginPage() {
    return "index_layouts/login";
  }

  @GetMapping("/signup")
  public String signupPage(UserCreateForm userCreateForm) {
    return "index_layouts/signup";
  }
  @PostMapping("/signup")
  public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult, HttpServletRequest request, Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("errorMessage", "Error message");
      return "index_layouts/signup";

    }
    if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
      bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
      return "index_layouts/signup";
    }

    try {

      userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
    } catch (DataIntegrityViolationException e){
      e.printStackTrace();
      bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
      return "index_layouts/signup";
    } catch (Exception e){
      e.printStackTrace();
      bindingResult.reject("signupFailed", e.getMessage());
      return "index_layouts/signup";
    }

    return "redirect:/";

  }
  @GetMapping("/about")
  public String signupPage(){
    return "index_layouts/about";
  }


}
