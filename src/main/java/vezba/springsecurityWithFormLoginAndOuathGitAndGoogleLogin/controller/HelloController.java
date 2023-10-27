package vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.model.AuthenticationProvider;
import vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.repository.AuthenticationProviderRepository;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private AuthenticationProviderRepository authenticationProviderRepository;

    //@ResponseBody
    @GetMapping("/afterLogin")          //salje nas ovde posle login forme, to sam konfigurisao u spring security klasi
    public String hello(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //proveravam da li se user logovao preko moje obicne forme ili preko githuba ili google-a preko oauth2
        if (authentication instanceof OAuth2AuthenticationToken) {// Ako se korisnik prijavio/logovao putem OAuth2(githuba ili google-a)
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            if (oauthToken.getAuthorizedClientRegistrationId().equals("google")) {
                model.addAttribute("userName", oauthToken.getPrincipal().getAttribute("name"));
                System.out.println("Rola usera koji se logovao preko google-a je " + oauthToken.getAuthorities());
                //u bazi, u tabeli authentication_provider, sacuvacu info ko se logovao, tj preko kog provajdera(da li preko moje forme ili spoljnih oauth2 github ili google)
                AuthenticationProvider authenticationProvider = AuthenticationProvider.AuthenticationProviderBuilder.aAuthenticationProvider().withAuthProvider("google").withUsername(oauthToken.getPrincipal().getAttribute("name")).build();
                authenticationProviderRepository.save(authenticationProvider);
            } else if (oauthToken.getAuthorizedClientRegistrationId().equals("github")) {
                model.addAttribute("userName", oauthToken.getPrincipal().getAttribute("login"));
                System.out.println("Rola usera koji se logovao preko github-a je " + oauthToken.getAuthorities());
                //u bazi, u tabeli authentication_provider, sacuvacu info ko se logovao, tj preko kog provajdera(da li preko moje forme ili spoljnih oauth2 github ili google)
                AuthenticationProvider authenticationProvider = AuthenticationProvider.AuthenticationProviderBuilder.aAuthenticationProvider().withAuthProvider("github").withUsername(oauthToken.getPrincipal().getAttribute("login")).build();
                authenticationProviderRepository.save(authenticationProvider);
            }
        } else if (authentication != null) {
            // Ako se korisnik prijavio na drugi način (npr. obična forma)
            model.addAttribute("userName", authentication.getName());
            System.out.println("Rola usera koji se logovao preko moje custom login forme je " + authentication.getAuthorities());
            //u bazi, u tabeli authentication_provider, sacuvacu info ko se logovao, tj preko kog provajdera(da li preko moje forme ili spoljnih oauth2 github ili google)
            AuthenticationProvider authenticationProvider = AuthenticationProvider.AuthenticationProviderBuilder.aAuthenticationProvider().withAuthProvider("moja login forma").withUsername(authentication.getName()).build();
            authenticationProviderRepository.save(authenticationProvider);
        }

        return "homePage";
    }



}
