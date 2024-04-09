package com.retroplanet.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Getter
@Setter
public class mainController {

  @GetMapping("/")
public String main(){
    return "index";
  }

  @GetMapping("/test1")
  public String test1(){
    return "user/content";
  }

  @GetMapping("/test2")
  public String test2(){
    return "user/content";
  }


  @GetMapping("/personalhome")
public String personalhome(Model model){
//    model.addAttribute("header", "user/header");
    model.addAttribute("left", "user/left");
//    model.addAttribute("center", "user/center");

return "user/minihome";
  }


  @GetMapping("/photoAlbum")
public String photoalbum(Model model){
//    model.addAttribute("header", "user/header");
    model.addAttribute("left", "user/left");
//    model.addAttribute("center", "user/center");

return "user/photoalbum/photoAlbum";
  }
  @GetMapping("/photoDetail")
  public String photoDetail(Model model){
//    model.addAttribute("header", "user/header");
    model.addAttribute("left", "user/left");
//    model.addAttribute("center", "user/center");

    return "user/photoalbum/photoDetail";
  }

  @GetMapping("/diary")
  public String diary(Model model){
//    model.addAttribute("header", "user/header");
    model.addAttribute("left", "user/left");
//    model.addAttribute("center", "user/center");

    return "user/diary/diary";
  }


}
