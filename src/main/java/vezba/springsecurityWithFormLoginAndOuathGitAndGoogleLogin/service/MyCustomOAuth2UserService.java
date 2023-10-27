package vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MyCustomOAuth2UserService extends DefaultOAuth2UserService{

    //ako zelim da dohvatim usera koji se logovao preko spoljnog provajdera za autentifikaciju, preko githuba oauth2, ako zelim npr da mu setujem rolu
    //kako bi znao gde sme da pristupa kojim endpointima po aplikaciji, napravim klasu, extende-ujem je klasom DefaultOAuth2UserService, override-ujem
    //metodu loadUser(), dohvatim usera i setujem mu role. Posle ovog samo dodam u setovanju za oauth2 da koristim moju custom implementaciju za usera
    //slicna ideja kao kada u spring security-u hocu da imam svoju custom tabelu/tabele za usere i onda samo napravim klasu koja implementira UserDetailsService
    //pa override-ujem metodu UserDetails loadUserByUsername(String username), tu je samo bitno da popunim usera username-om, password-om i rolom/rolama
    //samo sto u ovoj klasi, ovoj metodi loadUser mi ne moramo nista da setujemo ako ne zelimo, radice sve i bez ove klase ali ako zelimo nesto da radimo
    //sa tim userom nakon logovanja preko spoljnog provajdera(oauth2 preko githuba npr ovde), onda moramo detalje tog usera ovde da dohvatimo i da kazemo da
    //to radimo u security configu u delu za oauth2:

        //.oauth2Login()
        //                .loginPage("/users/showMyLoginPage")
        //                .defaultSuccessUrl("/hello/afterLogin", true)
        //                .userInfoEndpoint().userService(myCustomOAuth2UserService);       OVAJ RED


    //sada dodajemo ovde i deo za GOOGLE. Kada se user loguje preko spoljnog providera i izabere opciju google, mi cemo isto ovde dohvatiti usera
    //isto kao sto dohvatamo usera koji se logovao preko githuba. Logicno, u metodi loadUser(OAuth2UserRequest) jer i github i google rade preko
    //oauth2.



    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (userRequest.getClientRegistration().getRegistrationId().equals("github")) {
            // Obrada GitHub autentifikacije
            authorities.add(new SimpleGrantedAuthority("USER"));
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            // Obrada Google autentifikacije
            authorities.add(new SimpleGrantedAuthority("USER"));
            // Dodajte dodatnu logiku ili atribute za Google autentifikaciju
        } else {
            // Obrada za ostale provajdere
        }

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // Dodatna logika na osnovu atributa ili tipa autentifikacije

        return new DefaultOAuth2User(authorities, attributes, "login");
        //pojasnjenje ovog reda
        //kreirate novi objekat tipa DefaultOAuth2User, koji predstavlja korisnika koji se uspešno autentifikovao putem OAuth2 protokola.
        //Ovaj konstruktor kreira objekat DefaultOAuth2User koristeći sledeće argumente:

        //authorities: Ovo su dozvole ili uloge koje su dodeljene korisniku nakon autentifikacije. U ovom primeru, dodali smo ulogu "USER" korisniku koji se uspešno autentifikovao.
        //attributes: informacije o korisniku koji su dobijeni nakon uspešne autentifikacije putem OAuth2. Npr korisničko ime,email adresu ili druge info koje su dostupne putem provajdera autentifikacije.
        //"login": Ovo označava atribut koji će se koristiti kao identifikator korisnika. U vašem primeru, izgleda da ste odabrali atribut koji se naziva "login" kao identifikator korisnika.

        //Ova linija koda kreira objekat koji predstavlja korisnika sa odgovarajućim dozvolama (ulogama) i atributima (informacijama) koji su dostupni nakon uspešne
        //autentifikacije putem OAuth2 protokola. Ovaj objekat se može koristiti dalje u procesu autentifikacije i autorizacije unutar vaše aplikacije.
    }





                                    //PRIMER KADA JE SAMO BILA OPCIJA ZA LOGIN PREKO GITHUBA
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        //posto se korisnik ulogovao preko githuba preko oauth2(mi mu zakucamo da ima rolu obicnog usera). To cemo uraditi i za usera koji se logovao preko google-a
//        //Kada se korisnik prijavi putem GitHub-a pomoću OAuth2 autentifikacije, podaci o korisniku kao što su username i password se neće vratiti u odgovoru,
//        //jer se autentifikacija vrši spoljašnjim provajderom, u ovom slučaju GitHub-om, a ne lokalno vašom aplikacijom.
//        //Umesto toga, OAuth2 provajder će vratiti određene informacije o korisniku kao deo OAuth2 atributa. Ove informacije mogu uključivati username, email adresu,
//        //ID korisnika, dozvole ili uloge, ali neće sadržati password korisnika.
//
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//
//        // Uzmite atribute korisnika
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//
//        // Proverite i obradite uloge ili dozvole koje korisnik ima na GitHub-u
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("USER"));
//
//        System.out.println("U METODI loadUser klase MyCustomOAuth2UserService extends DefaultOAuth2UserService, userova rola: " + authorities.get(0).getAuthority());
//
//        // Dodajte odgovarajuće uloge ili dozvole na osnovu atributa koji se vraćaju prilikom autentifikacije putem GitHub-a
//        // Na primer, možete proveriti atribute kao što su scope ili permissions kako biste odredili uloge ili dozvole korisnika
//
//        return new DefaultOAuth2User(authorities, attributes, "login");
//        //return super.loadUser(userRequest);
//    }
}
