/*
 * package com.sample1.security;
 * 
 * import org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * 
 * @Override public void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth.inMemoryAuthentication().withUser("javainuse")
 * .password("javainuse").roles("USER"); }
 * 
 * 
 * 
 * @Override public void configure(HttpSecurity http) throws Exception {
 * http.antMatcher("/chatroom").authorizeRequests().anyRequest().hasRole("USER")
 * .and().formLogin().loginPage("/index") .permitAll().and().logout();
 * 
 * }
 * 
 * 
 * }
 */