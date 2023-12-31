package vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.model.AuthenticationProvider;

@Repository
public interface AuthenticationProviderRepository extends JpaRepository<AuthenticationProvider, Integer>{
}
