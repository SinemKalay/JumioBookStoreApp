package com.sinem.jumio.security;

import com.sinem.jumio.dataAccessObject.CustomerRepository;
import com.sinem.jumio.domainObject.CustomerDO;
import com.sinem.jumio.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    @Autowired
    private CustomerRepository customerRepo;


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        // All requests send to the Web Server request must be authenticated
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .authorizeRequests().antMatchers("/console/**").permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();

        // Use AuthenticationEntryPoint to authenticate user/password
        http.httpBasic().authenticationEntryPoint(authEntryPoint);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {

        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>
                mngConfig = auth.inMemoryAuthentication();

        Optional<List<CustomerDO>> customerDoList= customerRepo.findAllByDeleted(false);
        customerDoList.orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: "));

        Function<CustomerDO,UserDetails> customerToUser = s->makeUserDetails(s);

        List<UserDetails> userDetails = customerDoList.get().stream().map(customerToUser).collect(Collectors.toList());
        userDetails.stream().forEach(s->mngConfig.withUser(s));

    }

    private UserDetails makeUserDetails(CustomerDO customerDO){
        return User.withUsername(customerDO.getUsername()).password(this.passwordEncoder().encode(customerDO.getPassword())).roles("USER").build();
    }


}