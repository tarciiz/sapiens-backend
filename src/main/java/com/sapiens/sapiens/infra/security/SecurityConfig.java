package com.sapiens.sapiens.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        // Auth Controller
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/change-password").permitAll()

                        // Admin Controller
                        .requestMatchers("/api/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")

                        // SuperAdmin Controller
                        .requestMatchers("/api/super-admin/**").hasRole("SUPERADMIN")

                        // Attendance Controller
                        .requestMatchers("/api/attendance/save-one").hasAnyRole("ADMIN", "TEACHER", "SUPERADMIN")
                        .requestMatchers("/api/attendance/save-many").hasAnyRole("ADMIN", "TEACHER", "SUPERADMIN")
                        .requestMatchers("/api/attendance/update").hasAnyRole("ADMIN", "TEACHER", "SUPERADMIN")
                        .requestMatchers("/api/attendance/delete").hasAnyRole("ADMIN", "TEACHER", "SUPERADMIN")
                        .requestMatchers("/api/attendance/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT", "SUPERADMIN")

                        // Discipline Controller
                        .requestMatchers("/api/discipline/save").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers("/api/discipline/delete").hasAnyRole("ADMIN", "SUPERADMIN")

                        .requestMatchers("/api/discipline/teacher/**").hasAnyRole("ADMIN", "TEACHER", "SUPERADMIN")
                        .requestMatchers("/api/discipline/class/**")
                        .hasAnyRole("ADMIN", "TEACHER", "STUDENT", "SUPERADMIN")
                        .requestMatchers("/api/discipline/all").hasAnyRole("ADMIN", "TEACHER", "STUDENT", "SUPERADMIN")

                        // Evaluation Controller
                        .requestMatchers("/api/evaluation/save").hasAnyRole("ADMIN", "TEACHER", "SUPERADMIN")
                        .requestMatchers("/api/evaluation/update").hasAnyRole("ADMIN", "TEACHER", "SUPERADMIN")
                        .requestMatchers("/api/evaluation/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT", "SUPERADMIN")

                        // Grade Controller
                        .requestMatchers("/api/grade/save-one").hasAnyRole("ADMIN", "TEACHER", "SUPERADMIN")
                        .requestMatchers("/api/grade/save-many").hasAnyRole("ADMIN", "TEACHER", "SUPERADMIN")
                        .requestMatchers("/api/grade/update").hasAnyRole("ADMIN", "TEACHER", "SUPERADMIN")
                        .requestMatchers("/api/grade/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT", "SUPERADMIN")

                        // Lesson Controller
                        .requestMatchers("/api/lesson/save").hasAnyRole("ADMIN", "TEACHER", "SUPERADMIN")
                        .requestMatchers("/api/lesson/update").hasAnyRole("ADMIN", "TEACHER", "SUPERADMIN")
                        .requestMatchers("/api/lesson/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT", "SUPERADMIN")

                        // Schedule Controller
                        .requestMatchers("/api/schedule/save-one").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers("/api/schedule/save-many").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers("/api/schedule/save-many/discipline/{code}").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers("/api/schedule/update").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers("/api/schedule/delete").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers("/api/schedule/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT", "SUPERADMIN")

                        // School Class Controller
                        .requestMatchers("/api/school-class/save").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers("/api/school-class/update").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers("/api/school-class/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT", "SUPERADMIN")

                        // Student Controller
                        .requestMatchers("/api/student/save").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers("/api/student/update").hasAnyRole("ADMIN", "STUDENT", "SUPERADMIN")
                        .requestMatchers("/api/student/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER", "SUPERADMIN")

                        // Teacher Controller
                        .requestMatchers("/api/teacher/**").hasAnyRole("ADMIN", "TEACHER", "SUPERADMIN")

                        // School Controller
                        .requestMatchers("/api/school/save").hasRole("SUPERADMIN")
                        .requestMatchers("/api/school/update").hasRole("SUPERADMIN")
                        .requestMatchers("/api/school/all").hasRole("SUPERADMIN")
                        .requestMatchers("/api/school/**").hasAnyRole("ADMIN", "TEACHER", "SUPERADMIN", "STUDENT")

                        // Secretariat Controller
                        .requestMatchers("/api/secretariat/**").hasRole("SUPERADMIN")

                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration authConfiguration)
            throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

}