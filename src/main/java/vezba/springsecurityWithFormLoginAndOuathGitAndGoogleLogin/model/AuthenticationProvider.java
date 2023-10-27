package vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.model;

import javax.persistence.*;

@Entity
@Table(name = "authentication_provider")
public class AuthenticationProvider {

    //OVA KLASA TJ TABELA U BAZI SLUZI SAMO DA ZAPISEM INFORMACIJU, USER KOJI SE LOGOVAO, DA LI JE PREKO MOJE CUSTOM LOGIN FORME ILI PREKO SPOLJNIH
    //OAUTH2 PROVAJDERA(GITHUBA ILI GOOGLE-A). TO CU DA ZAPISEM U HelloController-u U METODI ENDPOINTA "/afterLogin" JER SAM KONFIGURISAO U SPRING
    //SECURITY CONFIG KLASI DA ME TAMO SALJE NAKON USPESNOG LOGINA

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    @Column(name = "auth_provider")
    private String authProvider;


    //konstruktor
    public AuthenticationProvider() {}



    //BUILDER PATTERN(builder static class)
    public static class AuthenticationProviderBuilder{
        private int id;
        private String username;
        private String authProvider;

        //KONSTRUKTORI
        private AuthenticationProviderBuilder(){}

        public static AuthenticationProviderBuilder aAuthenticationProvider(){
            return new AuthenticationProviderBuilder();
        }

        //with
        public AuthenticationProviderBuilder withId(int id){
            this.id = id;
            return this;
        }
        public AuthenticationProviderBuilder withUsername(String username){
            this.username = username;
            return this;
        }
        public AuthenticationProviderBuilder withAuthProvider(String authProvider){
            this.authProvider = authProvider;
            return this;
        }

        //BUILD
        public AuthenticationProvider build(){
            AuthenticationProvider authenticationProvider = new AuthenticationProvider();
            authenticationProvider.id = this.id;
            authenticationProvider.username = this.username;
            authenticationProvider.authProvider = this.authProvider;
            return authenticationProvider;
        }

    }

    //equals & hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthenticationProvider that = (AuthenticationProvider) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return authProvider != null ? authProvider.equals(that.authProvider) : that.authProvider == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (authProvider != null ? authProvider.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAuthProvider() {
        return authProvider;
    }
    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }
}
