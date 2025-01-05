INSERT INTO FAMILIES (NAME) VALUES ('Famille 1'), ('Famille 2');

-- Ajouter des articles
INSERT INTO ARTICLES (ID, NAME, DESCRIPTION, UNIT_PRICE, STOCK, STOCK_MIN, FAMILY_ID) VALUES
                                                                                          (1, 'Article 1', 'Description article 1', 10.5, 50, 5, 1),
                                                                                          (2, 'Article 2', 'Description article 2', 20.0, 30, 10, 2);

-- Ajouter des fournisseurs
INSERT INTO SUPPLIERS (ID, SUPPLIER_NAME, ADDRESS, EMAIL, PHONE_NUMBER) VALUES
                                                                            (1, 'Fournisseur 1', 'Adresse 1', 'fournisseur1@example.com', '0102030405'),
                                                                            (2, 'Fournisseur 2', 'Adresse 2', 'fournisseur2@example.com', '0607080910');

-- Associer des articles aux fournisseurs
INSERT INTO SUPPLIER_ARTICLE (SUPPLIER_ID, ARTICLE_ID) VALUES
                                                           (1, 1),
                                                           (1, 2),
                                                           (2, 2);

-- Ajouter des clients
INSERT INTO CUSTOMERS (ID, FIRST_NAME, LAST_NAME, EMAIL, ADDRESS, PHONE_NUMBER) VALUES
                                                                                    (1, 'Jean', 'Dupont', 'jean.dupont@example.com', 'Rue 1', '0101010101'),
                                                                                    (2, 'Marie', 'Curie', 'marie.curie@example.com', 'Rue 2', '0202020202');

-- Ajouter des commandes
INSERT INTO ORDERS (ID, IS_SUPPLIER_ORDER, ORDER_DATE, STATUS, CUSTOMER_ID, SUPPLIER_ID) VALUES
                                                                                             (1, FALSE, '2024-01-01 10:00:00', 'PENDING', 1, NULL),
                                                                                             (2, TRUE, '2024-01-02 15:30:00', 'DELIVERED', NULL, 1);

-- Ajouter des lignes de commande
INSERT INTO ORDER_LINES (ID, QUANTITY, ORDER_ID, ARTICLE_ID) VALUES
                                                                 (1, 2, 1, 1),
                                                                 (2, 5, 2, 2);
