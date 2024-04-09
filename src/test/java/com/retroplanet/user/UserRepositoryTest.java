//package com.retroplanet.user;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.List;
//import java.util.Optional;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
////#1. 통합 테스트를 위한 어노테이션
//@SpringBootTest
//class UserRepositoryTest {
//
//  @Autowired
//  UserRepository userRepository;
//
//  @Autowired
//  UserService userService;
//
//  @Test
//  @Transactional
//  public void 회원검색(){
////    this.아이템임의생성();
//    Optional<SiteUser> siteUser = userRepository.findByUserid("test");
//
//
//    assertTrue(siteUser.isPresent());
//    SiteUser user = siteUser.get();
//
//    System.out.println(user.getId());
//    System.out.println(user.getEmail());
//
//  }
//
//
//}