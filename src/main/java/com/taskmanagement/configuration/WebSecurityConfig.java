package com.taskmanagement.configuration;

import com.taskmanagement.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http

                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                              .requestMatchers( "/", "/register","/login","/css/**").permitAll()
                                .requestMatchers("/user/**").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(formLogin ->
                            formLogin
                                    .loginPage("/login")
                                    .loginProcessingUrl("/login")
                                    .failureUrl("/login?error=true")
                                    .defaultSuccessUrl("/user/tasks", true)
                                    .permitAll()
                )

                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout=true")
                                .permitAll()
                )

                .authenticationProvider(authenticationProvider());

                return http.build();


    }
}
