package com.retroplanet.user;

import com.retroplanet.DataNotFoundException;
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
  private final PasswordEncoder passwordEncoder;

  public SiteUser create(String username, String email, String password) {
    SiteUser user = new SiteUser();
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));
    this.userRepository.save(user);
    return user;

  }

  public SiteUser getUser(String username) {
    Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
    if(siteUser.isPresent()){
      return siteUser.get();
    } else {
      throw new DataNotFoundException("siteuser not found");
    }
  }


  public Optional<SiteUser> getCurrentUser() {
    // 현재 사용자의 인증 정보 가져오기
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    // 인증 정보에서 사용자 이름(username)을 가져와서 해당 사용자 정보 반환
    String username = authentication.getName();
    return userRepository.findByUsername(username);
  }
}
