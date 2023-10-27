package vezba.springsecurityWithFormLoginAndOuathGitAndGoogleLogin.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class MyDbUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    private String role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyDbUser user;

    //konstruktor
    public MyDbUserRole() {}



    //BUILDER PATTERN(builder static class)
    public static class MyDbUserRoleBuilder{
        private int roleId;
        private String role;
        private MyDbUser user;

        //KONSTRUKTORI
        private MyDbUserRoleBuilder(){}

        public static MyDbUserRoleBuilder aMyDbUserRole(){
            return new MyDbUserRoleBuilder();
        }

        //with
        public MyDbUserRoleBuilder withRoleId(int roleId){
            this.roleId = roleId;
            return this;
        }
        public MyDbUserRoleBuilder withRole(String role){
            this.role = role;
            return this;
        }
        public MyDbUserRoleBuilder withUser(MyDbUser user){
            this.user = user;
            return this;
        }

        //BUILD
        public MyDbUserRole build(){
            MyDbUserRole myDbUserRole = new MyDbUserRole();
            myDbUserRole.roleId = this.roleId;
            myDbUserRole.role = this.role;
            myDbUserRole.user = this.user;
            return myDbUserRole;
        }

    }

    //equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyDbUserRole that = (MyDbUserRole) o;

        if (roleId != that.roleId) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public MyDbUser getUser() {
        return user;
    }
    public void setUser(MyDbUser user) {
        this.user = user;
    }
}
