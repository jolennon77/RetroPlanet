package com.retroplanet.userprofile;

import com.retroplanet.DataNotFoundException;
import com.retroplanet.user.SiteUser;
import com.retroplanet.user.UserRepository;
import groovy.lang.GString;
import java.io.File;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class UserProfileService {

  private final UserProfileRepository userProfileRepository;
  private final UserRepository userRepository;
  private final String DEFAULT_PATH = System.getProperty("user.dir") + "/src/main/resources/static/files";


  public UserProfile saveUserProfile(SiteUser siteUser) {

    UserProfile userProfile = new UserProfile();
    userProfile.setSiteUser(siteUser);
    return userProfileRepository.save(userProfile);
  }


  public UserProfile getUserProfile(String username) {

    Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
    if (siteUser.isPresent()) {
      Optional<UserProfile> userProfile = this.userProfileRepository.findBySiteUser(siteUser.get());
      if (userProfile.isPresent()) {
        return userProfile.get();
      }
      throw new DataNotFoundException("userprofile not found");
    } else {
      throw new DataNotFoundException("userprofile not found");
    }
  }


  public void updateTodayMood(String username, String todayMood) {

    UserProfile userProfile = this.getUserProfile(username);

    userProfile.setTodayMood(todayMood);
    userProfile.setId(userProfile.getId());
    this.userProfileRepository.save(userProfile);

  }

  public void updateIntroduction(String username, String introduction) {

    UserProfile userProfile = this.getUserProfile(username);

    userProfile.setIntroduction(introduction);
    userProfile.setId(userProfile.getId());
    this.userProfileRepository.save(userProfile);

  }


  public void updateProfileImg(String username, MultipartFile file) throws Exception {

    String projectPath = DEFAULT_PATH + "/profileImg";

    UUID uuid = UUID.randomUUID();
    String fileName = uuid + "_" + file.getOriginalFilename();
    File saveFile = new File(projectPath, fileName);
    file.transferTo(saveFile);



    Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);

    if (siteUser.isPresent()) {

      Optional<UserProfile> userProfile = this.userProfileRepository.findBySiteUser(siteUser.get());

      userProfile.get().setProfileImgName(fileName);
      userProfile.get().setProfileImgPath("/files/profileImg/" + fileName);

      this.userProfileRepository.save(userProfile.get());
    } else {
      System.out.println("업로드 실패");
    }

  }

  public void updateMainPageImg(String username, MultipartFile file) throws Exception {

    String projectPath = DEFAULT_PATH + "/mainPageImg";

    UUID uuid = UUID.randomUUID();
    String fileName = uuid + "_" + file.getOriginalFilename();
    File saveFile = new File(projectPath, fileName);
    file.transferTo(saveFile);

    Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);

    if (siteUser.isPresent()) {

      Optional<UserProfile> userProfile = this.userProfileRepository.findBySiteUser(siteUser.get());

      userProfile.get().setMainPageImgName(fileName);
      userProfile.get().setMainPageImgPath("/files/mainPageImg/" + fileName);

      this.userProfileRepository.save(userProfile.get());
    } else {
      System.out.println("업로드 실패");
    }

  }



    public String getYouTubeURL(String url) {
      int lastIndex = url.lastIndexOf("/");
      if (lastIndex != -1 && lastIndex < url.length() - 1) {
        return url.substring(lastIndex + 1);
      }
      return null;
    }

    public void updateYouTubeUrl(String username, String url){
      String YouTubeUrl = this.getYouTubeURL(url);

      UserProfile userProfile = this.getUserProfile(username);

      userProfile.setYouTubeURL(YouTubeUrl);
      userProfile.setId(userProfile.getId());
      this.userProfileRepository.save(userProfile);

    }

}