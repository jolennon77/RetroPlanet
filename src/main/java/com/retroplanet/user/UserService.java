package com.retroplanet.user;

import com.retroplanet.DataNotFoundException;
import com.retroplanet.userprofile.UserProfile;
import com.retroplanet.userprofile.UserProfileRepository;
import com.retroplanet.userprofile.UserProfileService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserProfileService userProfileService;
  private final PasswordEncoder passwordEncoder;

  public SiteUser create(String username, String email, String password) {
    SiteUser user = new SiteUser();
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));
    this.userRepository.save(user);
    UserProfile userProfile = this.userProfileService.saveUserProfile(user);

    return user;

  }

  public SiteUser getUser(String username) {
    Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
//    if(siteUser.isPresent()){
//      return siteUser.get();
//    } else {
//     /* throw new DataNotFoundException("siteuser not found");*/
//      return null;
//    }
    return siteUser.orElse(null);
  }


  public Optional<SiteUser> getCurrentUser() {
    // 현재 사용자의 인증 정보 가져오기
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    // 인증 정보에서 사용자 이름(username)을 가져와서 해당 사용자 정보 반환
    String username = authentication.getName();
    return userRepository.findByUsername(username);
  }



  public void follow(Long followerId, Long followeeId) {


    SiteUser follower = this.userRepository.findById(followerId).get();         //유저1
    SiteUser followee = this.userRepository.findById(followeeId).get();        //유저2

    follower.getFollowing().add(followee);
    followee.getFollowers().add(follower);


    this.userRepository.save(followee);
    this.userRepository.save(follower);

  }
}
