//package com.retroplanet.user;
//
//
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Transactional
//class UserControllerTest {
//
//  @Autowired
//  private UserService userService;
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @Autowired
//  PasswordEncoder passwordEncoder;
//
//
////  @Test
////  public void 로그인테스트() throws Exception{
////    String userid = "test";
////    String password = "Sjrnfl77&7";
////    mockMvc.perform(formLogin().userParameter("userid")
////            .loginProcessingUrl("/members/login")
////            .user(userid).password(password))
////        .andExpect(SecurityMockMvcResultMatchers.authenticated());
////
////  }
//
//}