package com.surge.reggie.configuration;

import com.surge.common.Constant;
import com.surge.reggie.domain.Employee;
import com.surge.reggie.security.ProjectUsernamePasswordAuthenticationFilter;
import jakarta.servlet.http.HttpSession;
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

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ProjectUsernamePasswordAuthenticationFilter projectUsernamePasswordAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration, HttpSession httpSession) throws Exception {
        ProjectUsernamePasswordAuthenticationFilter filter = new ProjectUsernamePasswordAuthenticationFilter("/employee/login");
        filter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
        filter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            Employee employee = (Employee) authentication.getPrincipal();
            httpSession.setAttribute(Constant.SESSION_EMPLOYEE, employee);

        });
        return filter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, ProjectUsernamePasswordAuthenticationFilter projectUsernamePasswordAuthenticationFilter) throws Exception {
        httpSecurity
                .csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer
                                .disable()
                )
                .headers(httpSecurityHeadersConfigurer ->
                        httpSecurityHeadersConfigurer
                                .disable()
                )
                .addFilterBefore(projectUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/employee/login").permitAll()
                                .requestMatchers("/static/**").permitAll()
                                .anyRequest().authenticated()
                );
        return httpSecurity.build();
    }

}
