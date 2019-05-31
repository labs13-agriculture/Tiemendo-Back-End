package com.lambdaschool.tiemendo;

import com.github.javafaker.Faker;
import com.lambdaschool.tiemendo.model.*;
import com.lambdaschool.tiemendo.repository.RoleRepository;
import com.lambdaschool.tiemendo.repository.StaffRepository;
import com.lambdaschool.tiemendo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
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

        // Seed user roles
        Role role1 = new Role("admin");
        Role role2 = new Role("user");
        rolerepos.save(role1);
        rolerepos.save(role2);

        // build roles list for users
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), role1));
        admins.add(new UserRoles(new User(), role2));

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), role2));

        // Save and build test user and admin
        User u1 = new User("username", "password", users);
        User u2 = new User("admin", "password", admins);

        userrepos.save(u1);
        userrepos.save(u2);


        Faker faker = new Faker();

        //adding 25 seeded admin users, all with password "password"
        System.out.println("Made it to first faker loop");
        ArrayList<User> seedAdmins = new ArrayList<>();
        for(int i = 0; i < 25; i++)
        {
            ArrayList<UserRoles> admin = new ArrayList<>();
            admin.add(new UserRoles(new User(), role1));
            User newUser = new User(faker.name().firstName() + i, "password", admin);
            seedAdmins.add(newUser);
//            userrepos.save(newUser);
        }
        userrepos.saveAll(seedAdmins);

        System.out.println("Made it past first faker loop");
        //adding 475 seeded users, all with password "password"
        ArrayList<User> seedUsers = new ArrayList<>();
        for(int i = 0; i < 25; i++)
        {
            ArrayList<UserRoles> user = new ArrayList<>();
            user.add(new UserRoles(new User(), role2));
            User newUser = new User(faker.name().username() + i, "password", user);
            seedUsers.add(newUser);
//            userrepos.save(newUser);
        }
        userrepos.saveAll(seedUsers);
        System.out.println("Finished seed data");

        

    }
}

