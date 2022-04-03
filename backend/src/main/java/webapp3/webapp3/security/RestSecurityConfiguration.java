package webapp3.webapp3.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import webapp3.webapp3.security.jwt.JwtRequestFilter;

import java.security.SecureRandom;

@Configuration
@Order(1)
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    RepositoryUserDetailsService userDetailsService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/api/**");

        //exerciseTables
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/exercises/**").hasRole("monitor");

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/exercises-tables/**").hasRole("monitor");

        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/exercises/**").hasRole("monitor");

        //administrator
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/admin/statistics").hasRole("administrator");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/members/statistics").hasRole("administrator");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/monitors/**").hasRole("administrator");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/members?page={numPage}").hasRole("administrator");

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/monitors/**").hasRole("administrator");

        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/monitors/{id}").hasRole("administrator");

        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("administrator");

        //monitor
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/monitors/me").hasRole("monitor");

        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/me").hasRole("monitor");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/me/image/").hasRole("monitor");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/monitors/{id}/image/").hasRole("monitor");

        //member
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/members/me").hasRole("monitor");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/members/statistics").hasRole("member");

        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/me").hasRole("member");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/me/image/").hasRole("member");

        // Other URLs can be accessed without authentication
        http.authorizeRequests().anyRequest().permitAll();

        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf().disable();

        // Disable Http Basic Authentication
        http.httpBasic().disable();

        // Disable Form login Authentication
        http.formLogin().disable();

        // Avoid creating session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add JWT Token filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

