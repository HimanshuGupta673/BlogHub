package com.learner.config;

import com.learner.security.CustomUserDetailService;
import com.learner.security.JwtAuthenticationEntryPoint;
import com.learner.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.config.AuditingConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
    @Autowired
    private CustomUserDetailService customUserDetailService;
//
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         http.csrf(csrf -> csrf.disable())
                 .authorizeRequests()
                 .requestMatchers("/api/v1/auth/login").permitAll()
                 .requestMatchers(HttpMethod.GET).permitAll()
                 .anyRequest()
                 .authenticated()
                 .and().exceptionHandling(ex -> ex.authenticationEntryPoint(this.jwtAuthenticationEntryPoint))
                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
         http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//         http.authenticationProvider(daoAuthenticationProvider());
         DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
         return defaultSecurityFilterChain;
     }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.csrf().disable().authorizeHttpRequests().anyRequest().anthenticated().and().httpBasic();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
         DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
         provider.setUserDetailsService(this.customUserDetailService);
         provider.setPasswordEncoder(passwordEncoder());
         return provider;
    }


//    for jwt authentication...



}
