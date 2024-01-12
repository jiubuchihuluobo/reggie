package com.surge.reggie.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    @Bean
//    public EmployeeUsernamePasswordAuthenticationFilter projectUsernamePasswordAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        EmployeeUsernamePasswordAuthenticationFilter filter = new EmployeeUsernamePasswordAuthenticationFilter("/employee/login");
//        filter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
//        filter.setAuthenticationSuccessHandler(new EmployeeAuthenticationSuccessHandler());
//        filter.setAuthenticationFailureHandler(new EmployeeAuthenticationFailureHandler());
//        return filter;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .anonymous(httpSecurityAnonymousConfigurer ->
                        httpSecurityAnonymousConfigurer
                                .disable()
                )
                .csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer
                                .disable()
                )
                .headers(httpSecurityHeadersConfigurer ->
                        httpSecurityHeadersConfigurer
                                .disable()
                )
//                .addFilterBefore(employeeUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(Customizer.withDefaults())
                .rememberMe(Customizer.withDefaults())
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
