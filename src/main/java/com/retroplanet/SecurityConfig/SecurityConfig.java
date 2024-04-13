package com.retroplanet.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  @Autowired
  private UserAuthenticationSuccessHandler authenticationSuccessHandler;
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
            .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
        .csrf((csrf) -> csrf
            .ignoringRequestMatchers(
                new AntPathRequestMatcher("/upload/**"), // 파일 업로드 요청에 대한 CSRF 보호 제외
                new AntPathRequestMatcher("/h2-console/**") // H2 콘솔 요청에 대한 CSRF 보호 제외
            ))
        .headers((headers) -> headers.addHeaderWriter(
            new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
        .formLogin((formLogin) -> formLogin.loginPage("/login").successHandler(authenticationSuccessHandler))
        .logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/").invalidateHttpSession(true));
    ;
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
