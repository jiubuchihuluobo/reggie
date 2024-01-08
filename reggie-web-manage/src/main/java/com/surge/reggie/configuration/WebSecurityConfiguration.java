package com.surge.reggie.configuration;

import com.surge.reggie.security.EmployeeAuthenticationFailureHandler;
import com.surge.reggie.security.EmployeeAuthenticationSuccessHandler;
import com.surge.reggie.security.EmployeeUsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public EmployeeUsernamePasswordAuthenticationFilter projectUsernamePasswordAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        EmployeeUsernamePasswordAuthenticationFilter filter = new EmployeeUsernamePasswordAuthenticationFilter("/employee/login");
        filter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
        filter.setAuthenticationSuccessHandler(new EmployeeAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(new EmployeeAuthenticationFailureHandler());
        return filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, EmployeeUsernamePasswordAuthenticationFilter employeeUsernamePasswordAuthenticationFilter) throws Exception {
        SessionAuthenticationStrategy sessionAuthenticationStrategy = httpSecurity.getSharedObject(SessionAuthenticationStrategy.class);
        httpSecurity
//                .anonymous(httpSecurityAnonymousConfigurer ->
//                        httpSecurityAnonymousConfigurer
//                                .disable()
//
//                )
                .csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer
                                .disable()
                )
                .headers(httpSecurityHeadersConfigurer ->
                        httpSecurityHeadersConfigurer
                                .frameOptions().disable()
                )
                .addFilterBefore(employeeUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(Customizer.withDefaults())
//                .rememberMe(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/static/**").permitAll()
                                .requestMatchers("/error/**").permitAll()
                                .requestMatchers("/employee/login").permitAll()
                                .anyRequest().authenticated()
                );
        return httpSecurity.build();
    }

}
