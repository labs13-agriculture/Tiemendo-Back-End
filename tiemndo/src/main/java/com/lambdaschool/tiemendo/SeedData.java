package com.lambdaschool.tiemendo;

import com.github.javafaker.Faker;
import com.lambdaschool.tiemendo.model.*;
import com.lambdaschool.tiemendo.repository.*;
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
    RetailerRepository retailerRepository;
    RetailerContactRepository retailerContactRepository;
    RetailerLocationRepository retailerLocationRepository;
    
    public SeedData(RoleRepository rolerepos, UserRepository userrepos, StaffRepository staffrepos, RetailerRepository retailerRepository, RetailerContactRepository retailerContactRepository, RetailerLocationRepository retailerLocationRepository)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.staffrepos = staffrepos;
        this.retailerRepository = retailerRepository;
        this.retailerContactRepository = retailerContactRepository;
        this.retailerLocationRepository = retailerLocationRepository;
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
        
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), role1));
        
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), role2));
    
        rolerepos.save(role1);
        rolerepos.save(role2);
        
        User u1 = new User("username", "password", users);
        userrepos.save(u1);
        
        User u2 = new User("admin", "password", admins);
        userrepos.save(u2);
    
        Faker faker = new Faker();

        
        //adding 25 seeded admin users, all with password "password"
//        for(int i = 0; i < 25; i++)
//        {
//            ArrayList<UserRoles> admin = new ArrayList<>();
//            admin.add(new UserRoles(new User(), role1));
//            User newUser = new User(faker.name().firstName(), "password", admin);
//            userrepos.save(newUser);
//        }
//
//        //adding 475 seeded users, all with password "password"
//        for(int i = 0; i < 475; i++)
//        {
//            ArrayList<UserRoles> user = new ArrayList<>();
//            user.add(new UserRoles(new User(), role2));
//            User newUser = new User(faker.name().username(), "password", user);
//            userrepos.save(newUser);
//        }
        
        //ADDING RETAILERS WITH CONTACTS AND LOCATIONS
        RetailerLocation rl1 = new RetailerLocation("123 main street", "North", "District 2", "Downtown", "Crooked tree", new Retailer());
        //retailerLocationRepository.save(rl1);
        
        RetailerContact rc1 = new RetailerContact("Mr", "John Doe", "M", "Ghanaian", "01/01/2010", "PhD", "Manager", "555-555-5555", "email@example.com", new Retailer());
        //retailerContactRepository.save(rc1);
        
        Retailer r1 = new Retailer("Lowes", 1989, false, rl1, rc1);
        
        rc1.setRetailer(r1);
        rl1.setRetailer(r1);
        
        retailerRepository.save(r1);
        

    }
}

