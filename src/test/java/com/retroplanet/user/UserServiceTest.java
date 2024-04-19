package com.retroplanet.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.retroplanet.DataNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserServiceTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;


  @Test
  @Transactional
  void 유저가입테스트(){
    SiteUser user1 = new SiteUser();

    user1.setEmail("test1@gmail.com");
    user1.setUsername("test1");
    user1.setPassword(passwordEncoder.encode("test12345"));
    this.userRepository.save(user1);
  }

  @Test
  @Transactional
  void 유저찾기테스트(){
    Optional<SiteUser> user = this.userRepository.findById(3L);
    if(user.isPresent()){
      SiteUser user1 = user.get();
      assertEquals("test1", user1.getUsername());

    }
  }


  @Test
  @Transactional
  void 유저찾기이름() {
    Optional<SiteUser> siteUser = this.userRepository.findByUsername("test1");
    if(siteUser.isPresent()){

      System.out.println(siteUser.get().getId());
      System.out.println(siteUser.get().getUsername());

    }
  }

  @Test
  @Transactional
  void 유저삭제테스트(){
    this.userRepository.deleteById(16L);
  }
}