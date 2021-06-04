package com.acme.interlab.configuration;

import com.acme.interlab.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userdetailservice;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/index.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userdetailservice);
    }

    private static final String[] AUTH_LIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/swagger-resources/**",
            "/swagger-ui.html**",
            "swagger-ui**",
            "swagger-ui/**",
            "/swagger.ui**",
            "/swagger-ui/",
            "/swagger-ui/**",
            "**/swagger-ui/",
            "**/swagger-ui/**",
            "swagger-ui.css",
            "/swagger-ui.css",
            "/interlab-api-docs",
            "/interlab-api-docs**",
            "/interlab-api-docs/**",
            "/interlab-api-docs/",
            "/interlab-api-docs/swagger-config",
            "/interlab-api-docs/swagger-config**",
            "/interlab-api-docs/swagger-config/",
            "/interlab-api-docs/swagger-config/**",
            "/index.html?configUrl=/interlab-api-docs/swagger-config",
            "index.html?configUrl=/interlab-api-docs/swagger-config",
            "/webjars/**",
            "favicon.ico","/css/**", "/login", "/"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
                .antMatchers(AUTH_LIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new LoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)

                .addFilterBefore(new JwtFilter(),
                        UsernamePasswordAuthenticationFilter.class);

    }



}
