package com.demo.ngsoft;

import com.demo.ngsoft.entities.Role;
import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.errorHandler.RestResponseEntityExceptionHandler;
import com.demo.ngsoft.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.util.Optional;

@ComponentScan(basePackages = {"com.demo.ngsoft"})
@EntityScan(basePackages = "com.demo.ngsoft.entities")
@EnableJpaRepositories(basePackages = "com.demo.ngsoft.repositories")
@Import(RestResponseEntityExceptionHandler.class)
@RequiredArgsConstructor
@Configuration
public class NgSoftConfiguration {
/*

private final UserRepo userRepo;

    @Bean
    public UserDetailsService getUserDetailsService(){
        UserDetailsService userDetailsService = new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<User> user =  userRepo.findByEmail(username);
                User curr=null;
                if(user.isPresent()){
                     curr = user.get();
                    curr.setRole(Role.chooseRole(curr.isAdmin()));
                }else{
                    throw new UsernameNotFoundException("The user: "+username+" not found");
                }

                return curr;
            }
        };
        return userDetailsService;
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(getUserDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration conf) throws Exception {
        return conf.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
       return  new BCryptPasswordEncoder();
    }
*/


}
