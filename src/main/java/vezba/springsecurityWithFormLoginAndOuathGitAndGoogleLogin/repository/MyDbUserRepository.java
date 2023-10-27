package vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.model.MyDbUser;

@Repository
public interface MyDbUserRepository extends JpaRepository<MyDbUser, Integer>{

    MyDbUser findByUsername(String username);

}
