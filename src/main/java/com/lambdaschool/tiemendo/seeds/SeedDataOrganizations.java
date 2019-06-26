 package com.lambdaschool.tiemendo.seeds;

import com.github.javafaker.Faker;
import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationBranch;
import com.lambdaschool.tiemendo.repository.OrganizationBranchRepository;
import com.lambdaschool.tiemendo.repository.OrganizationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@Component
public class SeedDataOrganizations implements CommandLineRunner
{
    private OrganizationRepository organizationRepository;
    private OrganizationBranchRepository orgBranchRepo;



    public SeedDataOrganizations(OrganizationRepository organizationRepository, OrganizationBranchRepository orgBranchRepo)
    {
        this.organizationRepository = organizationRepository;
        this.orgBranchRepo = orgBranchRepo;

    }

    @Override
    public void run(String[] args) throws Exception
    {
        System.out.println("Seeding Organizations Data");

        var f = new Faker();

        // no for Number Organizations
        for (var no=0; no<50; no++){
            var org = new Organization(
                    f.company().name(),
                    f.bool().bool(),
                    f.address().fullAddress(),
                    f.number().numberBetween(20,100)
            );

            org = organizationRepository.save(org);

            // nb for number of branches
            for(var nb=0; nb<1 + (int) Math.floor(Math.random() * 12); nb++) {
                var branch = new OrganizationBranch(
                        f.harryPotter().character(),
                        f.phoneNumber().cellPhone(),
                        f.internet().emailAddress(),
                        f.options().option("CEO", "Director", "Sales", "Organizer"),
                        f.address().streetAddress(),
                        f.address().city(),
                        f.address().state(),
                        f.harryPotter().location(),
                        org
                );

                org.getBranches().add(orgBranchRepo.save(branch));
            }
        }

    }
}