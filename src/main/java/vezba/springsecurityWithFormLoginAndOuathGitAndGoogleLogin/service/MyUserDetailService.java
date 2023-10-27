package vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.model.MyDbUser;
import vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.model.MyDbUserRole;
import vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.repository.MyDbRoleRepository;
import vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.repository.MyDbUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService{

    @Autowired
    private MyDbUserRepository myDbUserRepository;

    @Autowired
    private MyDbRoleRepository myDbRoleRepository;

    //moji useri iz baze, kada se loginujem preko moje custom forme na loginu
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //user i njegove role iz baze, moje 2 custom tabele "user" & "role"
        MyDbUser myDbUser = myDbUserRepository.findByUsername(username);
        List<MyDbUserRole> myDbUserRoles = myDbRoleRepository.findByUser(myDbUser);
        System.out.println("ROLAAAAAAA " + myDbUserRoles.get(0).getRole() + " " + myDbUserRoles.get(1).getRole());

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(int i=0; i<myDbUserRoles.size(); i++){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(myDbUserRoles.get(i).getRole());
            grantedAuthorities.add(simpleGrantedAuthority);
        }

        UserDetails user = User.withUsername(myDbUser.getUsername()).password(myDbUser.getPassword()).authorities(grantedAuthorities).build();
        return user;
    }


    //na pocetku sam zakucao
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //radi vezbe cu hardkodovati usera
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ADMIN");
//        SimpleGrantedAuthority roleUser = new SimpleGrantedAuthority("USER");
//        authorities.add(roleAdmin);
//        authorities.add(roleUser);
//        UserDetails user = MyDbUser.withUsername("igor").password("igor").authorities(authorities).build();
//        return user;
//    }
}
