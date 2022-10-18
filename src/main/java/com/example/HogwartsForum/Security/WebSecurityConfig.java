package com.example.HogwartsForum.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder, JwtUserDetailsService jwtUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http
                .csrf()
                .disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // filters
        http
                .addFilter(new UsernameAndPasswordAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(), UsernameAndPasswordAuthenticationFilter.class);
        // matchers
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll();

        // student-side
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/student").permitAll()
                .antMatchers(HttpMethod.GET, "/student").hasAnyRole("STUDENT", "ADMIN")
                .antMatchers(HttpMethod.GET, "/student/{id}").hasAnyRole("STUDENT", "ADMIN")
                .antMatchers(HttpMethod.GET, "/student/get-favorite-companies/**").hasAnyRole("STUDENT", "ADMIN")
                .antMatchers(HttpMethod.POST, "/student/add-favorite-company/**").hasAnyRole("STUDENT", "ADMIN")
                .antMatchers(HttpMethod.POST, "/job/**").hasAnyRole("STUDENT", "ADMIN")
                .antMatchers(HttpMethod.GET, "/job/list-all").hasAnyRole("STUDENT", "ADMIN");

        // company-side
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/company").permitAll()
                .antMatchers(HttpMethod.GET, "/company").hasAnyRole("COMPANY", "ADMIN")
                .antMatchers(HttpMethod.GET, "/company/**").hasAnyRole("COMPANY", "ADMIN")
                .antMatchers(HttpMethod.POST, "/company/add-favorite-student/**").hasAnyRole("COMPANY", "ADMIN");


        http
                .authorizeRequests()

                .antMatchers(HttpMethod.GET, "/job").permitAll()
                .antMatchers(HttpMethod.GET, "/job/**").permitAll();

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(jwtUserDetailsService);
        return provider;
    }
}
