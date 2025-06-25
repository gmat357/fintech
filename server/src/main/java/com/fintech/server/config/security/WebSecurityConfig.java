package com.fintech.server.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .httpBasic(
        httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.disable()
      )
      .csrf(
        csrfConfigurer -> csrfConfigurer.disable()
      )
      .sessionManagement(
        httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .headers(
        headerConfig ->
          headerConfig.frameOptions(
          frameOptionsConfig -> frameOptionsConfig.disable()
        )
      )
      .authorizeHttpRequests(
        authorizeRequest ->
          authorizeRequest
            .requestMatchers("/**").permitAll()
            .anyRequest().authenticated()

      );

    return http.build();
  }
}
