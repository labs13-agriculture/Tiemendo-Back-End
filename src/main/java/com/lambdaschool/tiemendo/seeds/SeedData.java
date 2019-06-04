//package com.lambdaschool.tiemendo.seeds;
//
//import com.github.javafaker.Faker;
//import com.lambdaschool.tiemendo.model.*;
//import com.lambdaschool.tiemendo.repository.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Transactional
//@Component
//public class SeedData implements CommandLineRunner
//{
//    OrganizationRepository organizationRepository;
//    OrganizationContactRepository organizationContactRepository;
//    OrganizationLocationRepository organizationLocationRepository;
//
//
//
//    public SeedData(OrganizationRepository organizationRepository, OrganizationContactRepository organizationContactRepository, OrganizationLocationRepository organizationLocationRepository)
//    {
//        this.organizationRepository = organizationRepository;
//        this.organizationContactRepository = organizationContactRepository;
//        this.organizationLocationRepository = organizationLocationRepository;
//
//    }
//
//    @Override
//    public void run(String[] args) throws Exception
//    {
//        //ORGANIZATION TESTING BELOW
//        OrganizationContact oc1 = new OrganizationContact("Denise", "111-555-1234", "email@example.com", "Fundraising director", new Organization());
//        List<OrganizationContact> oc1list = new ArrayList<>();
//        oc1list.add(oc1);
//
//        OrganizationLocation ol1 = new OrganizationLocation("555 Organization Road", "Org town", "North side", "4-way intersection", new Organization());
//        List<OrganizationLocation> ol1list = new ArrayList<>();
//        ol1list.add(ol1);
//
//        Organization o1 = new Organization("FarmersOnly", false, ol1list, "", 17, oc1list);
//
//        oc1.setOrganization(o1);
//        ol1.setOrganization(o1);
//
//        organizationRepository.save(o1);
//        organizationContactRepository.save(oc1);
//        organizationLocationRepository.save(ol1);
//
//    }
//}
//
