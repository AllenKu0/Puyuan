package com.example.demo1.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    /**
     # 用戶認證配置 #
     1. authorizeHttpRequests()方法：對所有訪問HTTP端點的HttpServletRequest進行限制
     2. anyRequest().authenticated()語句指定了對於所有請求都需要執行認證，也就是說沒有通過認證的用戶就無法訪問任何端點。
     3. httpBasic(), 允許用戶使用HTTP基礎認證(Basic Authentication)進行身分驗證。
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
            .disable()
            .authorizeHttpRequests()
            .requestMatchers(
                "/error/**",
                "/api/register",
                "/api/auth",
                "/api/verification/send",
                "/api/verification/check",
                "/v3/api-docs/**",
                "/swagger-ui/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
