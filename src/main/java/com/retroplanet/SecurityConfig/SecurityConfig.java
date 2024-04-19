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
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  @Autowired
  private UserAuthenticationSuccessHandler authenticationSuccessHandler;
  @Autowired
  private DefaultOAuth2UserService defaultOAuth2UserService;

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
            .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/oauth2/**")).permitAll())

        .oauth2Login((oauth2) -> oauth2
            .authorizationEndpoint((endpoint)-> endpoint.baseUri("/api/v1/auth/oauth2"))
            .redirectionEndpoint((endpoint) -> endpoint.baseUri("/oauth2/callback/*"))
            .userInfoEndpoint((endpoint) -> endpoint.userService(defaultOAuth2UserService))
            .successHandler(authenticationSuccessHandler)
            .loginPage("/login") // 기존 로그인 페이지 경로 지정 하지않으면 Login with OAuth 2.0 페이지로 로드됨
        )
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
