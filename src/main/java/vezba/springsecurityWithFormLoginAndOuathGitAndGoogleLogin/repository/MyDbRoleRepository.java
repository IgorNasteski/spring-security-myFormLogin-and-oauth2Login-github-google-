package vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.model.MyDbUser;
import vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.model.MyDbUserRole;

import java.util.List;

@Repository
public interface MyDbRoleRepository extends JpaRepository<MyDbUserRole, Integer>{

    List<MyDbUserRole> findByUser(MyDbUser user);
}
