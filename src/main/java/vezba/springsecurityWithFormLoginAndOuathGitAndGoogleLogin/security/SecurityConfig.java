package vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.service.MyCustomOAuth2UserService;
import vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.service.MyUserDetailService;

@EnableWebSecurity//(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private MyCustomOAuth2UserService myCustomOAuth2UserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/img/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/users/showMyLoginPage").permitAll()                        //moraju da imaju istu putanju ka login stranici
                    .loginProcessingUrl("/users/checkUserCredentialsAtLogin")               //ne moram da pravim endpoint metode kontrolera, dovoljno je da u thymeleaf login formi stavim ovaj isti endpoint        <form th:action="@{/users/checkUserCredentialsAtLogin}"
                    .defaultSuccessUrl("/hello/afterLogin", true)
                .and()
                .oauth2Login()                                                              //za svaki spoljasnji provajder koji radi preko oauth2(u mom slucaju za github i google)
                    .loginPage("/users/showMyLoginPage")                                    //moraju da imaju istu putanju ka login stranici
                    .defaultSuccessUrl("/hello/afterLogin", true)
                                                                                            //ne mora ovde .loginProcessingUrl() jer moja aplikacija ne radi autentifikaciju za one koji se loginuju preko spoljnjeg provajdera, oni to rade
                    .userInfoEndpoint().userService(myCustomOAuth2UserService);
        http
                .logout()
                    .logoutUrl("/logout").permitAll();

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

}
