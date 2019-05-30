//package com.lambdaschool.tiemendo;
//
//import com.lambdaschool.tiemendo.model.*;
//import com.lambdaschool.tiemendo.repository.RoleRepository;
//import com.lambdaschool.tiemendo.repository.StaffRepository;
//import com.lambdaschool.tiemendo.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//
//@Transactional
//@Component
//public class SeedData implements CommandLineRunner
//{
//    RoleRepository rolerepos;
//    UserRepository userrepos;
//    StaffRepository staffrepos;
//
//    public SeedData(RoleRepository rolerepos, UserRepository userrepos, StaffRepository staffrepos)
//    {
//        this.rolerepos = rolerepos;
//        this.userrepos = userrepos;
//        this.staffrepos = staffrepos;
//    }
//
//    @Override
//    public void run(String[] args) throws Exception
//    {
////        Staff staff1 = new Staff("Chase Garsee");
////        staff1.getClients().add(new Client("Space For Dummies"));
////        staffrepos.save(staff1);
//
//        Role role2 = new Role("user");
//
//        ArrayList<UserRoles> users = new ArrayList<>();
//        users.add(new UserRoles(new User(), role2));
//
//        User u2 = new User("garsee", "password", users);
//
//        userrepos.save(u2);
//
//    }
//}
//
