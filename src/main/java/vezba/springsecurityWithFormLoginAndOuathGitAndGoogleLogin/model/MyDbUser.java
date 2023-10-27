package vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class MyDbUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user")       //polje u klasi MyDbUserRole je     private MyDbUser user;
    private List<MyDbUserRole> roles;   //user je referenca u toj klasi, pa u mappedBy("ovde") stavljamo user

    //konstruktor
    public MyDbUser() {
    }



    //BUILDER PATTERN(builder static class)
    public static class MyDbUserBuilder{
        private int userId;
        private String username;
        private String password;
        private List<MyDbUserRole> roles;

        //KONSTRUKTORI
        private MyDbUserBuilder(){}

        public static MyDbUserBuilder aMyDbUser(){
            return new MyDbUserBuilder();
        }

        //with
        public MyDbUserBuilder withUserId(int userId){
            this.userId = userId;
            return this;
        }
        public MyDbUserBuilder withUsername(String username){
            this.username = username;
            return this;
        }
        public MyDbUserBuilder withPassword(String password){
            this.password = password;
            return this;
        }
        public MyDbUserBuilder withRoles(List<MyDbUserRole> roles){
            this.roles = roles;
            return this;
        }

        //BUILD
        public MyDbUser build(){
            MyDbUser myDbUser = new MyDbUser();
                myDbUser.userId = this.userId;
                myDbUser.username = this.username;
                myDbUser.password = this.password;
                myDbUser.roles = this.roles;
            return myDbUser;
        }

    }

    //EQUALS I HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyDbUser myDbUser = (MyDbUser) o;

        if (userId != myDbUser.userId) return false;
        if (username != null ? !username.equals(myDbUser.username) : myDbUser.username != null) return false;
        if (password != null ? !password.equals(myDbUser.password) : myDbUser.password != null) return false;
        return roles != null ? roles.equals(myDbUser.roles) : myDbUser.roles == null;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<MyDbUserRole> getRoles() {
        return roles;
    }
    public void setRoles(List<MyDbUserRole> roles) {
        this.roles = roles;
    }
}
