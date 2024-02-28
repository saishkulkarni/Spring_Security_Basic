package org.jsp.security_sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfiguration {

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager getUserDetailsService() {
        UserDetails user1 = User.withUsername("user").password(encoder().encode("123")).roles("USER").build();
        UserDetails user2 = User.withUsername("admin").password(encoder().encode("admin")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.authorizeHttpRequests(
                req -> req.requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/user").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/").permitAll());
        security.formLogin(Customizer.withDefaults());
        return security.build();
    }

}
