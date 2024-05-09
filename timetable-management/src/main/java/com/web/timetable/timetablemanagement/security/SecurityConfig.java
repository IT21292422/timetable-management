package com.web.timetable.timetablemanagement.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    //private final JwtAuthFilter jwtAuthFilter;
    private JwtAuthEntryPoint authEntryPoint;
    private CustomerUserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthEntryPoint authEntryPoint, CustomerUserDetailsService userDetailsService) {
        this.authEntryPoint = authEntryPoint;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        //Auth
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/auth/register").hasRole("Admin")
                        //Booking
                        .requestMatchers(HttpMethod.GET, "/api/booking/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.POST, "/api/booking/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.PUT, "/api/booking/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.DELETE, "/api/booking/**").hasAnyRole("Admin","Faculty")
                        //Course
                        .requestMatchers(HttpMethod.GET, "/api/course/**").hasAnyRole("Admin","Student","Faculty")
                        .requestMatchers(HttpMethod.POST, "/api/course/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.PUT, "/api/course/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.PUT, "/api/course/assignSession/**").hasAnyRole("Faculty","Admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/course/**").hasRole("Admin")
                        //Enrollment
                        .requestMatchers(HttpMethod.GET, "/api/enrollment/**").hasAnyRole("Admin","Student","Faculty")
                        .requestMatchers(HttpMethod.POST, "/api/enrollment/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.PUT, "/api/enrollment/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.DELETE, "/api/enrollment/**").hasAnyRole("Admin","Faculty")
                        //Faculty
                        .requestMatchers(HttpMethod.GET, "/api/faculty/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.POST, "/api/faculty/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.PUT, "/api/faculty/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/faculty/**").hasRole("Admin")
                        //Notification
                        .requestMatchers(HttpMethod.GET, "/api/notification/**").hasAnyRole("Admin","Student","Faculty")
                        .requestMatchers(HttpMethod.POST, "/api/notification/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.PUT, "/api/notification/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.DELETE, "/api/notification/**").hasAnyRole("Admin","Faculty")
                        //Resource
                        .requestMatchers(HttpMethod.GET, "/api/resource/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.POST, "/api/resource/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.PUT, "/api/resource/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/resource/**").hasRole("Admin")
                        //Role
                        .requestMatchers(HttpMethod.GET, "/api/role/**").hasAnyRole("Admin")
                        .requestMatchers(HttpMethod.POST, "/api/role/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.PUT, "/api/role/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/role/**").hasRole("Admin")
                        //Room
                        .requestMatchers(HttpMethod.GET, "/api/room/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.POST, "/api/room/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.PUT, "/api/room/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/room/**").hasRole("Admin")
                        //Session
                        .requestMatchers(HttpMethod.GET, "/api/session/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.POST, "/api/session/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.PUT, "/api/session/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.DELETE, "/api/session/**").hasAnyRole("Admin","Faculty")
                        //Student
                        .requestMatchers(HttpMethod.GET, "/api/student/**").hasAnyRole("Admin","Student","Faculty")
                        .requestMatchers(HttpMethod.POST, "/api/student/**").hasAnyRole("Admin","Student","Faculty")
                        .requestMatchers(HttpMethod.PUT, "/api/student/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/student/**").hasRole("Admin")
                        //Timetable
                        .requestMatchers(HttpMethod.GET, "/api/timetable/**").hasAnyRole("Admin","Student","Faculty")
                        .requestMatchers(HttpMethod.POST, "/api/timetable/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.PUT, "/api/timetable/**").hasAnyRole("Admin","Faculty")
                        .requestMatchers(HttpMethod.DELETE, "/api/timetable/**").hasAnyRole("Admin","Faculty")
                        .anyRequest().authenticated());

        http.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(){
        return new JwtAuthFilter();
    }
}
