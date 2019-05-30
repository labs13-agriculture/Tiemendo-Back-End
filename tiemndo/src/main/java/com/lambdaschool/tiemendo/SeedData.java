package com.lambdaschool.tiemendo;

import com.github.javafaker.Faker;
import com.lambdaschool.tiemendo.model.*;
import com.lambdaschool.tiemendo.repository.RoleRepository;
import com.lambdaschool.tiemendo.repository.StaffRepository;
import com.lambdaschool.tiemendo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    RoleRepository rolerepos;
    UserRepository userrepos;
    StaffRepository staffrepos;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos, StaffRepository staffrepos)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.staffrepos = staffrepos;
    }

    @Override
    public void run(String[] args) throws Exception
    {
    
        System.out.println("Made it to seed data");
//        Staff staff1 = new Staff("Chase Garsee");
//        staff1.getClients().add(new Client("Space For Dummies"));
//        staffrepos.save(staff1);

        Role role1 = new Role("admin");
        Role role2 = new Role("user");
        
        rolerepos.save(role1);
        rolerepos.save(role2);
        
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), role1));
        
        

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), role2));
    
        Faker faker = new Faker();
    
        System.out.println("Made it to first faker loop");
        //adding 25 seeded admin users, all with password "password"
        for(int i = 0; i < 25; i++)
        {
            User newUser = new User(faker.name().firstName(), "password", admins);
            userrepos.save(newUser);
        }
        System.out.println("Made it past first faker loop");
        //adding 475 seeded users, all with password "password"
        for(int i = 0; i < 475; i++)
        {
            System.out.println(i);
            User newUser = new User(faker.name().username(), "password", users);
            userrepos.save(newUser);
        }
        System.out.println("Finished seed data");

    }
}

