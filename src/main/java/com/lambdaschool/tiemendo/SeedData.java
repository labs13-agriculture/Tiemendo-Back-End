package com.lambdaschool.tiemendo;

import com.github.javafaker.Faker;
import com.lambdaschool.tiemendo.model.*;
import com.lambdaschool.tiemendo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
    OrganizationRepository organizationRepository;
    OrganizationContactRepository organizationContactRepository;
    OrganizationLocationRepository organizationLocationRepository;
    
    
    public SeedData(RoleRepository rolerepos, UserRepository userrepos, StaffRepository staffrepos, RetailerRepository retailerRepository, RetailerContactRepository retailerContactRepository, RetailerLocationRepository retailerLocationRepository, OrganizationRepository organizationRepository, OrganizationContactRepository organizationContactRepository, OrganizationLocationRepository organizationLocationRepository)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.staffrepos = staffrepos;
        this.retailerRepository = retailerRepository;
        this.retailerContactRepository = retailerContactRepository;
        this.retailerLocationRepository = retailerLocationRepository;
        this.organizationRepository = organizationRepository;
        this.organizationContactRepository = organizationContactRepository;
        this.organizationLocationRepository = organizationLocationRepository;
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
    
    
        //ADDING RETAILERS WITH CONTACTS AND LOCATIONS
        RetailerLocation rl1 = new RetailerLocation("123 main street", "North", "District 2", "Downtown", "Crooked tree", new Retailer());
        //retailerLocationRepository.save(rl1);
    
        RetailerContact rc1 = new RetailerContact("Mr", "John Doe", "M", "Ghanaian", "01/01/2010", "PhD", "Manager", "555-555-5555", "email@example.com", new Retailer());
        //retailerContactRepository.save(rc1);
    
        Retailer r1 = new Retailer("Lowes", 1989, false, rl1, rc1);
    
        rc1.setRetailer(r1);
        rl1.setRetailer(r1);
    
        retailerRepository.save(r1);
        
        
        //ORGANIZATION TESTING BELOW
        OrganizationContact oc1 = new OrganizationContact("Denise", "111-555-1234", "email@example.com", "Fundraising director", new Organization());
        List<OrganizationContact> oc1list = new ArrayList<>();
        oc1list.add(oc1);
        
        OrganizationLocation ol1 = new OrganizationLocation("555 Organization Road", "Org town", "North side", "4-way intersection", new Organization());
        List<OrganizationLocation> ol1list = new ArrayList<>();
        ol1list.add(ol1);
        
        Organization o1 = new Organization(ol1list, "", 17, false, oc1list);
        
        oc1.setOrganization(o1);
        ol1.setOrganization(o1);
        
        organizationRepository.save(o1);
        organizationContactRepository.save(oc1);
        organizationLocationRepository.save(ol1);
        

    }
}

