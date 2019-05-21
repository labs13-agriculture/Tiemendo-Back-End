INSERT INTO staff(staffid, staffname)
        VALUES  (1, 'Frank'),
                (2, 'Bill'),
                (3, 'Willie');

INSERT INTO client(clientid, firstname, lastname, village, loanamount, lid, ldd, maizeinventory, maizegoal)
        VALUES (1, 'Elon', 'Musk', 'Las Angeles, CA', '$100,000,000', 'January 1st', 'December 31st', 1000, 1),
               (2, 'Jordan', 'Belfort', 'The Bronx, NY', '$50,000,000', 'February 6th', 'March 31st', 0, 100000),
               (3, 'Robert', 'Downy', 'Malibu, CA', '$10,500,000', 'July 4th', 'October 1st', 426, 265),
               (4, 'Lady', 'Gaga', 'New York, NY', '$15,350,553', 'January 1st', 'December 31st', 121, 111),
               (5, 'Frank', 'Sinatra', 'Hoboken, NJ', '$100,000,000', 'January 1st', 'December 31st', 356, 254),
               (6, 'Bill', 'Gates', 'Seattle, WA', '$900,000,000', 'May 4th', 'May 5th', 500, 400);



INSERT INTO roles(roleid, name)
        VALUES ( 1, 'user' );


INSERT INTO users(userid, username, password)
    VALUES ( 10, 'chasegarsee', '$2a$10$3kcNphf6EXQuwDMF5wO0weyL8QUltasTS.dvSEdtL5LBzyOhdKdqy'),
           (11, 'vlad', '$2a$10$bBPJjROovkaNyLDbjBT1cenWf/cqZzlE6.GKqS47CEAzB3ZHVmWrW');




alter sequence hibernate_sequence restart with 20;