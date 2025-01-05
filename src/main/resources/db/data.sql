-- Ajout de familles
INSERT INTO families (id, name) VALUES (1, 'Famille A'), (2, 'Famille B');

-- Ajout d'articles
INSERT INTO articles (id, name, description, stock, stock_min, unit_price, family_id)
VALUES
    (1, 'Article 1', 'Description Article 1', 50, 10, 10.99, 1),
    (2, 'Article 2', 'Description Article 2', 30, 5, 20.50, 2);

-- Ajout de clients
INSERT INTO customers (id, first_name, last_name, email, phone_number, address)
VALUES
    (1, 'Jean', 'Dupont', 'jean.dupont@example.com', '0102030405', '1 Rue Exemple'),
    (2, 'Marie', 'Curie', 'marie.curie@example.com', '0607080910', '2 Rue Exemple');

-- Ajout de fournisseurs
INSERT INTO suppliers (id, supplier_name, phone_number, email, address)
VALUES
    (1, 'Fournisseur A', '0708091011', 'fournisseurA@example.com', '5 Rue Fournisseur'),
    (2, 'Fournisseur B', '0809101112', 'fournisseurB@example.com', '6 Rue Fournisseur');

-- Ajout de commandes
INSERT INTO orders (id, order_date, is_supplier_order, status, customer_id, supplier_id)
VALUES
    (1, '2025-01-01 10:00:00', FALSE, 'CONFIRMED', 1, NULL),
    (2, '2025-01-02 11:30:00', TRUE, 'PENDING', NULL, 2);

-- Ajout de lignes de commande
INSERT INTO order_lines (id, quantity, order_id, article_id)
VALUES
    (1, 2, 1, 1),
    (2, 5, 2, 2);
