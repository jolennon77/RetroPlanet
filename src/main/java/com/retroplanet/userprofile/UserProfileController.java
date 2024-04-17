package com.retroplanet.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UserProfileController {

  private final UserProfileService userProfileService;

  @PostMapping("/profile")
  public String updateUserprofile(@RequestParam("username") String username,
                                 @RequestParam("todaymood") String todaymood,
                                 @RequestParam("youtubeurl") String youtubeurl,
                                 MultipartFile file, Model model) throws Exception {

    if(file != null && !file.isEmpty()) {
      this.userProfileService.updateProfileImg(username, file);
    }

    if(todaymood != null && !todaymood.isEmpty()) {

      this.userProfileService.updateTodayMood(username, todaymood);
    }

    if(youtubeurl != null && !youtubeurl.isEmpty()) {

      this.userProfileService.updateYouTubeUrl(username, youtubeurl);
    }
    return "redirect:/"+username+"/profilesetting";
}


  @PostMapping("/mainpage")
  public String updateMainProfile(@RequestParam("username") String username,
      @RequestParam("introduction") String introduction,
  MultipartFile file, Model model) throws Exception {

    if(file != null && !file.isEmpty()) {
      this.userProfileService.updateMainPageImg(username, file);
    }

   if(introduction != null && !introduction.isEmpty()) {

      this.userProfileService.updateIntroduction(username, introduction);
    }
    return "redirect:/"+username+"/mainpagesetting";
  }
}