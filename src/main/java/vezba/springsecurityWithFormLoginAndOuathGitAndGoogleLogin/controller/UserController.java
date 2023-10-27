package vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.dto.UserDTO;

@Controller
@RequestMapping("/users")
public class UserController {

    //SPRING SECURITY NAS PRVO SALJE NA OVU PUTANJU /showMyLoginPage A TO SMO DEFINISALI U config klasi SecurityConfiguration.java, DA NAS PRVO SALJE TAMO PRI LOGIN-U
    //vise ne jer sam u config security klasi setovao da svi mogu da vide sve, a da kliknu na regi/logi dugme samo ako zele(dodacu deo za mene/admina da samo ja vidim)
    @GetMapping("/showMyLoginPage")
    public String showLoginPage(Model theModel){
        //AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        //theModel.addAttribute("authRequest", authenticationRequest);
        System.out.println("U login metodi /showMyLoginPage kontrolera UserController");
        //UserDto klasu sam pravio cisto da pokupim username & password koje je korisnik uneo u login formi. Samo je bitno da se u toj klasi polja
        //nazovu tacno "username" i "password"
        theModel.addAttribute("user", new UserDTO());
        return "myLogin";
    }

    //SPRING SECURITY SAM ODRADI OVO U POZADINI, NE MORA DA SE PISE UOPSTE OVAJ ENDPOINT, DOVOLJNO JE DA NA thymeleaf STRANICI ZA LOGIN DODAM OVAJ ENDPOINT
    //dolazi ovde sa login forme, ako je uneo dobre kredencijale baci ga na home page "/hello/afterLogin",
//    @PostMapping("/checkUserCredentialsAtLogin")
//    public String checkUserCredentialsAfterLogin(@ModelAttribute("user")UserDTO user/*, BindingResult bindingResult*/, RedirectAttributes ra){
//        System.out.println("USAO U /checkUserCredentialsAtLogin, username " + user.getUsername() + " password " + user.getPassword());
//        //Users usersFromDb = userRepository.findByUsername(users.getUsername());
//        //System.out.println("USER IZ BAZE " + usersFromDb.getUsername() + " " + usersFromDb.getPassword());
//        String thymeleafStranica = "";
//
//        if(user == null){
//            //ne postoji taj users, mora opet da se loguje ili registruje
//            //thymeleafStranica = "nijeUneoDobarPassPriLoginuIliGaNemaUBazi";
//            System.out.println("NEMA USERA PRI LOGINU, OPET LOGOVANJE");
//            ra.addFlashAttribute("message1", "Invalid username, try again!");
//            thymeleafStranica = "redirect:/users/showMyLoginPage";
//        }
//        //ako postoji users u bazi sa ovim username-om i ako je uneo dobar password
//        else{ //if(usersFromDb != null && usersFromDb.getPassword().equals(users.getPassword())){
//            System.out.println("UNEO JE DOBAR PASSWORD, ULOGUJ GA");
//            thymeleafStranica = "redirect:/hello/afterLogin";
//        }
//        /*else if(usersFromDb != null && !usersFromDb.getPassword().equals(users.getPassword())){
//            System.out.println("NIJE UNEO DOBAR PASSWORD");
//            //thymeleafStranica = "nijeUneoDobarPassPriLoginuIliGaNemaUBazi";
//            ra.addFlashAttribute("message2", "Invalid password, try again!");
//            thymeleafStranica = "redirect:/users/showMyLoginPage";
//        }*/
//
//        return thymeleafStranica;
//    }

}
