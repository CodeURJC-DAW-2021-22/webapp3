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
            //administrator
        /*http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").hasAnyRole("administrator");*/
        http.authorizeRequests().antMatchers("/statistics").hasAnyRole("administrator");

        http.authorizeRequests().antMatchers("/activities").hasAnyRole("administrator");
        http.authorizeRequests().antMatchers("/addNewActivity").hasAnyRole("administrator");
        http.authorizeRequests().antMatchers("/editActivity/{id}").hasAnyRole("administrator");
        http.authorizeRequests().antMatchers("/activity/{id}").hasAnyRole("administrator");

        http.authorizeRequests().antMatchers("/monitors").hasAnyRole("administrator");
        http.authorizeRequests().antMatchers("/addNewMonitor").hasAnyRole("administrator");
        http.authorizeRequests().antMatchers("/monitor/{id}").hasAnyRole("administrator");
        http.authorizeRequests().antMatchers("/editMonitor/{id}").hasAnyRole("administrator");

        http.authorizeRequests().antMatchers("/clients").hasAnyRole("administrator");

            //monitor
        http.authorizeRequests().antMatchers("/MONschedule{id}").hasAnyRole("monitor");

        http.authorizeRequests().antMatchers("/MONprofile/{id}").hasAnyRole("monitor");
        http.authorizeRequests().antMatchers("/MONeditProfile").hasAnyRole("monitor");
        http.authorizeRequests().antMatchers("/MONeditProfile/{id}").hasAnyRole("monitor");

        http.authorizeRequests().antMatchers("/MONexerciseTable").hasAnyRole("monitor");
        http.authorizeRequests().antMatchers("/MONexerciseTable/{id}").hasAnyRole("monitor");

        http.authorizeRequests().antMatchers("/MONaddNewExerciseTable").hasAnyRole("monitor");
        http.authorizeRequests().antMatchers("/MONeditExerciseTable/{id}").hasAnyRole("monitor");

        http.authorizeRequests().antMatchers("/MONeditActivity/{id}").hasAnyRole("monitor");
        http.authorizeRequests().antMatchers("/MONexerciseTable/delete/{id}").hasAnyRole("monitor");

        http.authorizeRequests().antMatchers("/MONactivities").hasAnyRole("monitor");
        http.authorizeRequests().antMatchers("/MONactivity/{id}").hasAnyRole("monitor");
        http.authorizeRequests().antMatchers("/MONactivity/{id}/image").hasAnyRole("monitor");

            //member
        http.authorizeRequests().antMatchers("/MEMexercise").hasAnyRole("member");
        http.authorizeRequests().antMatchers("/MEMexercise/{id}/image").hasAnyRole("member");
        http.authorizeRequests().antMatchers("/MEMexerciseTable/{id}/pdf").hasAnyRole("member");

        http.authorizeRequests().antMatchers("/MEMeditProfile").hasAnyRole("member");
        http.authorizeRequests().antMatchers("/MEMeditProfile/{id}").hasAnyRole("member");
        http.authorizeRequests().antMatchers("//MEM/{id}/image").hasAnyRole("member");

        http.authorizeRequests().antMatchers("/MEMprofile/{id}").hasAnyRole("member");

        http.authorizeRequests().antMatchers("/MEMstatistics").hasAnyRole("member");

        http.authorizeRequests().antMatchers("/MEMactivities").hasAnyRole("member");
        http.authorizeRequests().antMatchers("/MEMactivity/{id}/image").hasAnyRole("member");



        // Login form
        http.formLogin().loginPage("/USR_log_in");
        http.formLogin().usernameParameter("email");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/USR_mainpage");
        http.formLogin().failureUrl("/USR_log_inError");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/USR_mainpage");
    }
}
