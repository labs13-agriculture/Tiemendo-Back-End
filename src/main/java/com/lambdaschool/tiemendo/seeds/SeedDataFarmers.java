package com.lambdaschool.tiemendo.seeds;

import com.github.javafaker.Faker;
import com.lambdaschool.tiemendo.model.*;
import com.lambdaschool.tiemendo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Transactional
@Component
public class SeedDataFarmers implements CommandLineRunner
{
    private ClientRepository clientRepo;
    private ItemTypeRepository itemRepo;


    public SeedDataFarmers(ClientRepository clientRepo, ItemTypeRepository itemRepo) {
        this.clientRepo = clientRepo;
        this.itemRepo = itemRepo;
    }

    @Override
    public void run(String[] args)
    {
        Faker f = new Faker();
        System.out.println("Seeding Farmer And Reatiler Data");
        //Farmer SEEDING

        // create other objects needed for client seed data
        // create ItemTypes for Transaction data
        ItemType sack = itemRepo.findByNameIgnoreCase("Knapsack");
        ItemType urea = itemRepo.findByNameIgnoreCase("Urea");
        ItemType cutlass = itemRepo.findByNameIgnoreCase("Cutlasses");
        ArrayList<ItemType> items = new ArrayList<>(Arrays.asList(sack, urea, cutlass));

        // make an array of 200 clients
        var clients = new ArrayList<Client>();
        String[] types = {"FARMER", "RETAILER"};

        for (var i=0; i++<15;) {
            // Create a client
            Client client = new Client(f.bool().bool(), (long) Math.ceil(Math.random() * 20) + 2000,
                    f.address().streetAddress(), f.gameOfThrones().house(), f.name().firstName(),
                    f.phoneNumber().phoneNumber(), f.internet().emailAddress());

            client.setType(types[(int)Math.floor(Math.random() * 2)]);

            // Set address info
            client.setSecondName(f.name().lastName());
            client.setCommunity(f.address().streetName());
            client.setRegion(f.address().city());
            client.setDistrict(f.address().state());
            client.setNationality(f.address().country());

            // this seems like theres got to be a better way to do this
            client.setDateofbirth(LocalDate.ofInstant(f.date().birthday(16, 40).toInstant(), ZoneId.systemDefault()));
            client.setGender(f.options().option("male", "female"));
            client.setEducationlevel(f.options().option("None", "Primary", "Secondary", "Trade", "Degree"));
            client.setTitle(f.options().option("CEO", "Director", "Sales", "Organizer", "Mr.", "Sir"));

            // This variable adds a multiplier to transactions and installments
            var extra = (int) Math.floor(Math.random() * 3);
            // Add Transactions to client
            // create list of transactions
            var transactions = new ArrayList<Transaction>();
            // loop to create 5 transactions
            for (var nt=0; nt++<5+extra;) {
                // create list on inputs
                var inputs = new ArrayList<TransactionItem>();
                // create transaction
                var transaction = new Transaction(f.options().option("CASH", "CREDIT", "CREDIT", "CREDIT"), new Date(), new ArrayList<>(), "Joshua", client);
                // generate inputs to add to list
                for (var ni=0; ni++<2;) {
                    TransactionItem ti = new TransactionItem(
                            f.number().numberBetween(1, 3),
                            items.get(ni-1),
                            f.number().randomDouble(2, 2, 10),
                            transaction
                    );
                    inputs.add(ti);
                }
                // add list of inputs to transaction
                transaction.setInputs(inputs);
                transactions.add(transaction);
            }
            // add transaction to client
            client.setTransactions(transactions);

            var installments = new ArrayList<Installment>();

            for (var ni=0; ni<1+extra; ni++){
                var installment = new Installment(
                        f.number().randomDouble(2, 5, 15),
                        new Date(),
                        f.options().option("MTN", "CASH", "BANK"),
                        f.starTrek().character(),
                        client
                );

                installments.add(installment);
            }

            client.setInstallments(installments);
            // add client to list
            clients.add(client);
        }

        clientRepo.saveAll(clients);
    }
}

