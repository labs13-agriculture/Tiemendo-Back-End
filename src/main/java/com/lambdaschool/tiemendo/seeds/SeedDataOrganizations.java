//package com.lambdaschool.tiemendo.seeds;
//
//import com.lambdaschool.tiemendo.model.Organization;
//import com.lambdaschool.tiemendo.model.OrganizationContact;
//import com.lambdaschool.tiemendo.model.OrganizationLocation;
//import com.lambdaschool.tiemendo.repository.OrganizationContactRepository;
//import com.lambdaschool.tiemendo.repository.OrganizationLocationRepository;
//import com.lambdaschool.tiemendo.repository.OrganizationRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.List;
//
//@Transactional
//@Component
//public class SeedDataOrganizations implements CommandLineRunner
//{
//    private OrganizationRepository organizationRepository;
//    private OrganizationContactRepository organizationContactRepository;
//    private OrganizationLocationRepository organizationLocationRepository;
//
//
//
//    public SeedDataOrganizations(OrganizationRepository organizationRepository, OrganizationContactRepository organizationContactRepository, OrganizationLocationRepository organizationLocationRepository)
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
//        System.out.println("Seeding Organizations Data");
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
