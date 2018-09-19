/**
 * CREATE Script for init of DB
 */

-- Create 3 Customers
INSERT INTO customer (id, date_created, username, password, deleted) VALUES (1, now(), 'sinem', 'kalay',
                                                                             FALSE);

INSERT INTO customer (id, date_created, username, password, deleted) VALUES (2, now(), 'secil', 'kalay',
                                                                             FALSE);

INSERT INTO customer (id, date_created, username, password, deleted) VALUES (3, now(), 'güller', 'kalay',
                                                                             FALSE);

INSERT INTO customer (id, date_created, username, password, deleted) VALUES (4, now(), 'eşref', 'kalay',
                                                                             FALSE);
--Stock 3 records
INSERT INTO stock (id, number_of_copy) VALUES (1, 56);
INSERT INTO stock (id, number_of_copy) VALUES (2, 456);
INSERT INTO stock (id, number_of_copy) VALUES (3, 34);

--Author 3 records
INSERT INTO author (id, first_name, last_name, deleted, date_created) VALUES (1, 'jack', 'kerouac', FALSE, now());
INSERT INTO author (id, first_name, last_name, deleted, date_created) VALUES (2, 'george', 'orwell', FALSE, now());
INSERT INTO author (id, first_name, last_name, deleted, date_created) VALUES (3, 'albert', 'camus', FALSE, now());

-- Create 3 books
INSERT INTO book (id, isbn, title, deleted, Author_ID, date_created, stock_ID)
VALUES (1, '12sddf3q3464545', 'On The Road',
        FALSE, 1, now(), 1);

INSERT INTO book (id, isbn, title, deleted, Author_ID, date_created, stock_ID) VALUES (2, 'asfaef23214fdf', '1984',
                                                                                       FALSE, 1, now(), 2);

INSERT INTO book (id, isbn, title, deleted, Author_ID, date_created, stock_ID) VALUES (3, 'gnymyum45345yg', 'The ' ||
                                                                                                            'Outsider',
                                                                                       FALSE, 1, now(), 3);

-- Create 6 Orders
INSERT INTO orders (id, order_status, Customer_ID, date_created, deleted) VALUES (1, 'CREATED', 1, now(),
                                                                                  FALSE);

INSERT INTO orders (id, order_status, Customer_ID, date_created, deleted) VALUES (2, 'CREATED', 1, now(),
                                                                                  FALSE);

INSERT INTO orders (id, order_status, Customer_ID, date_created, deleted) VALUES (3, 'CREATED', 1, now(),
                                                                                  FALSE);

INSERT INTO orders (id, order_status, Customer_ID, date_created, deleted) VALUES (4, 'CREATED', 1, now(),
                                                                                  FALSE);

INSERT INTO orders (id, order_status, Customer_ID, date_created, deleted) VALUES (5, 'CREATED', 1, now(),
                                                                                  FALSE);

INSERT INTO orders (id, order_status, Customer_ID, date_created, deleted) VALUES (6, 'CREATED', 1, now(),
                                                                                  FALSE);

--Create Join table Order_book

INSERT INTO order_item (id, Book_ID, Order_ID, quantity) VALUES (1, 1, 1, 3);
INSERT INTO order_item (id, Book_ID, Order_ID, quantity) VALUES (7, 2, 1, 4);
INSERT INTO order_item (id, Book_ID, Order_ID, quantity) VALUES (2, 1, 2, 3);
INSERT INTO order_item (id, Book_ID, Order_ID, quantity) VALUES (3, 1, 3, 3);
INSERT INTO order_item (id, Book_ID, Order_ID, quantity) VALUES (4, 1, 4, 3);
INSERT INTO order_item (id, Book_ID, Order_ID, quantity) VALUES (5, 1, 5, 3);
INSERT INTO order_item (id, Book_ID, Order_ID, quantity) VALUES (6, 1, 6, 3);
INSERT INTO order_item (id, Book_ID, Order_ID, quantity) VALUES (8, 3, 6, 1);








