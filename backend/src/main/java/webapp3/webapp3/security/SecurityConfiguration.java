package webapp3.webapp3.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    RepositoryUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Public pages
        http.authorizeRequests().antMatchers("/USR_mainpage").permitAll();

        http.authorizeRequests().antMatchers("/USR_activities").permitAll();
        http.authorizeRequests().antMatchers("/USR_activities/schedule/{id}").permitAll();

        http.authorizeRequests().antMatchers("/USR_prices").permitAll();

        http.authorizeRequests().antMatchers("/USR_contact_us").permitAll();

        http.authorizeRequests().antMatchers("/USR_log_in").permitAll();
        http.authorizeRequests().antMatchers("/USR_log_inError").permitAll();

        http.authorizeRequests().antMatchers("/USR_sign_in").permitAll();

        // Private pages
        //Admin
        http.authorizeRequests().antMatchers("/statistics").hasAnyRole("ADMIN");

        http.authorizeRequests().antMatchers("/activities").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/addNewActivity").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/editActivity/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/activity/{id}").hasAnyRole("ADMIN");

        http.authorizeRequests().antMatchers("/monitors").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/addNewMonitor").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/monitor/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/editMonitor/{id}").hasAnyRole("ADMIN");

        http.authorizeRequests().antMatchers("/clients").hasAnyRole("ADMIN");
        //Monitor
        //Client

        // Login form
        http.formLogin().loginPage("/USR_log_in");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/USR_mainpage");
        http.formLogin().failureUrl("/USR_log_inError");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/USR_mainpage");
    }
}
