INSERT INTO staff(staffid, staffname)
        VALUES  (1, 'Frank'),
                (2, 'Bill'),
                (3, 'Willie');


INSERT INTO client(clientid, firstname, lastname, village, loanamount, lid, ldd, maizeinventory, maizegoal)
        VALUES (1, 'Bill', 'Gates', 'LA', '$1,000,000', 'May 4th', 'May 5th', 500, 400);


alter sequence hibernate_sequence restart with 20;