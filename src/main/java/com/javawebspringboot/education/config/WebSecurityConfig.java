package com.javawebspringboot.education.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests().antMatchers("/login", "/logout").permitAll();
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        http.authorizeRequests().and().formLogin().loginPage("/login").loginProcessingUrl("/dang-nhap")
                .defaultSuccessUrl("/loginHandler").failureUrl("/login?error").usernameParameter("username")
                .passwordParameter("password");

        http.logout().logoutUrl("/logout").deleteCookies("JSESSIONID").logoutSuccessUrl("/login");
        http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(2);

        http.authorizeRequests().antMatchers("/student/**").access("hasRole('ROLE_STUDENT')");
        http.authorizeRequests().antMatchers("/lecturer/**").access("hasRole('ROLE_LECTURER')");
        http.authorizeRequests().antMatchers("/manage/**").access("hasRole('ROLE_MANAGE')");
        http.authorizeRequests().anyRequest().authenticated();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login/**");
    }

}
