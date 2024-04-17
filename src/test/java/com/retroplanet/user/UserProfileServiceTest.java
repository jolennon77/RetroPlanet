package com.retroplanet.user;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.retroplanet.DataNotFoundException;
import com.retroplanet.userprofile.UserProfile;
import com.retroplanet.userprofile.UserProfileRepository;
import com.retroplanet.userprofile.UserProfileService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserProfileServiceTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserProfileRepository userProfileRepository;

  @Autowired
  private UserProfileService userProfileService;


  @Test
  @Transactional
  void 유저프로필찾기테스트() {
    Optional<SiteUser> user = this.userRepository.findById(3L);

    assertTrue(user.isPresent());
    System.out.println(user.get().getId());
    SiteUser user1 = user.get();


  }


@Test
@Transactional
  public void getUserProfile() {

  String username = "test1";
  Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
  if (siteUser.isPresent()) {
    Optional<UserProfile> userProfile = this.userProfileRepository.findBySiteUser(siteUser.get());
    userProfile.ifPresent(profile -> System.out.println(profile.getId()));
  }
}



  @Test
  @Transactional
  void 유저프로필등록테스트() {
    Optional<SiteUser> user = this.userRepository.findById(1L);
    assertTrue(user.isPresent());
    SiteUser user1 = user.get();



    UserProfile userProfile = new UserProfile();

    userProfile.setSiteUser(user1);
    this.userProfileRepository.save(userProfile);

  }


}