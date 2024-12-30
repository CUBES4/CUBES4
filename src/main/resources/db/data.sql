-- Ajouter des familles
INSERT INTO families (id, name) VALUES (1, 'Famille 1');
INSERT INTO families (id, name) VALUES (2, 'Famille 2');

-- Ajouter des articles
INSERT INTO articles (id, name, description, unit_price, stock, stock_min, family_id)
VALUES (1, 'Article 1', 'Description article 1', 10.5, 50, 5, 1);
INSERT INTO articles (id, name, description, unit_price, stock, stock_min, family_id)
VALUES (2, 'Article 2', 'Description article 2', 20.0, 30, 10, 2);

-- Ajouter des fournisseurs
INSERT INTO suppliers (id, name, address, email, phone)
VALUES (1, 'Fournisseur 1', 'Adresse 1', 'fournisseur1@example.com', '0102030405');
INSERT INTO suppliers (id, name, address, email, phone)
VALUES (2, 'Fournisseur 2', 'Adresse 2', 'fournisseur2@example.com', '0607080910');

-- Ajouter des clients
INSERT INTO customers (id, first_name, last_name, email, address, phone)
VALUES (1, 'Jean', 'Dupont', 'jean.dupont@example.com', 'Rue 1', '0101010101');
INSERT INTO customers (id, first_name, last_name, email, address, phone)
VALUES (2, 'Marie', 'Curie', 'marie.curie@example.com', 'Rue 2', '0202020202');

-- Ajouter des commandes
INSERT INTO orders (id, order_date, is_supplier_order, status, customer_id, supplier_id)
VALUES (1, '2024-01-01 10:00:00', FALSE, 'En cours', 1, NULL);
INSERT INTO orders (id, order_date, is_supplier_order, status, customer_id, supplier_id)
VALUES (2, '2024-01-02 15:30:00', TRUE, 'Terminé', NULL, 1);

-- Ajouter des lignes de commande
INSERT INTO order_lines (id, quantity, order_id, article_id)
VALUES (1, 2, 1, 1);
INSERT INTO order_lines (id, quantity, order_id, article_id)
VALUES (2, 5, 2, 2);
