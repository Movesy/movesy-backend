package com.movesy.movesybackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/*").hasRole("ADMIN")
                .antMatchers("/user/edit").hasRole("USER")
                .antMatchers("/package/all").hasRole("TRANSPORTER")
                .antMatchers("/package/").hasRole("TRANSPORTER")
                .antMatchers("/package/create").hasRole("USER")
                .antMatchers("/package/edit/").hasRole("USER")
                .antMatchers("/package/delete/").hasRole("USER")
                .antMatchers("/package/user/").hasRole("USER")
                .antMatchers("/review/").hasRole("USER")
                .antMatchers("/review/create").hasRole("USER")
                .antMatchers("/review/delete").hasRole("USER")
                .antMatchers("/review/edit").hasRole("USER")
                .antMatchers("/review/transporter/").hasRole("USER")
                .antMatchers("/offer/").hasRole("USER")
                .antMatchers("/offer/create/").hasRole("TRANSPORTER")
                .antMatchers("/offer/edit/").hasRole("TRANSPORTER")
                .antMatchers("/offer/delete/").hasRole("TRANSPORTER")
                .anyRequest().authenticated();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailsService);

        return daoAuthenticationProvider;

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
