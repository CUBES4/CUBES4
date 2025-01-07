-- Ajout de familles
INSERT INTO families (name) VALUES
                                ('Famille A'),
                                ('Famille B'),
                                ('Rouge'),
                                ('Rosé'),
                                ('Blanc'),
                                ('Pétillants'),
                                ('Digestifs'),
                                ('Bières'),
                                ('Cidres'),
                                ('Spiritueux'),
                                ('Cocktails'),
                                ('Sans Alcool');

-- Ajout d'articles
INSERT INTO articles (id, name, description, stock, stock_min, unit_price, family_id)
VALUES
    (1, 'Château Margaux', 'Vin rouge français de Bordeaux', 50, 10, 249.99, 3),
    (2, 'Champagne Moët', 'Champagne pétillant de renom', 30, 5, 89.50, 6),
    (3, 'Perrier Jouët', 'Champagne Rosé élégant', 20, 3, 120.75, 4),
    (4, 'Côte du Rhône', 'Vin blanc sec et fruité', 40, 8, 15.99, 5),
    (5, 'Pastis 51', 'Apéritif anisé typiquement français', 25, 5, 19.99, 7),
    (6, 'Heineken', 'Bière blonde légère', 200, 50, 2.50, 8),
    (7, 'Cidre Rosé', 'Cidre léger et fruité', 120, 30, 3.50, 9),
    (8, 'Whisky Glenfiddich', 'Whisky écossais single malt', 15, 2, 45.99, 10),
    (9, 'Piña Colada', 'Cocktail prêt à servir', 50, 10, 8.99, 11),
    (10, 'Jus d’Orange', 'Boisson sans alcool', 100, 20, 1.99, 12);

-- Ajout de clients
INSERT INTO customers (id, first_name, last_name, email, phone_number, address)
VALUES
    (1, 'Jean', 'Dupont', 'jean.dupont@example.com', '0102030405', '1 Rue Exemple'),
    (2, 'Marie', 'Curie', 'marie.curie@example.com', '0607080910', '2 Rue Exemple'),
    (3, 'Albert', 'Einstein', 'albert.einstein@example.com', '0203040506', '3 Rue Relativité'),
    (4, 'Ada', 'Lovelace', 'ada.lovelace@example.com', '0304050607', '4 Rue Programmation'),
    (5, 'Grace', 'Hopper', 'grace.hopper@example.com', '0405060708', '5 Rue Informatique');

-- Ajout de fournisseurs
INSERT INTO suppliers (id, supplier_name, phone_number, email, address)
VALUES
    (1, 'Fournisseur A', '0708091011', 'fournisseurA@example.com', '5 Rue Fournisseur'),
    (2, 'Fournisseur B', '0809101112', 'fournisseurB@example.com', '6 Rue Fournisseur'),
    (3, 'Fournisseur C', '0909111213', 'fournisseurC@example.com', '7 Rue Importateur'),
    (4, 'Fournisseur D', '1011121314', 'fournisseurD@example.com', '8 Rue Exportateur'),
    (5, 'Fournisseur E', '1112131415', 'fournisseurE@example.com', '9 Rue International');

-- Ajout de commandes
INSERT INTO orders (id, order_date, is_supplier_order, status, customer_id, supplier_id)
VALUES
    (1, '2025-01-01 10:00:00', FALSE, 'CONFIRMED', 1, NULL),
    (2, '2025-01-02 11:30:00', TRUE, 'PENDING', NULL, 2),
    (3, '2025-01-03 14:15:00', FALSE, 'SHIPPED', 2, NULL),
    (4, '2025-01-04 09:45:00', TRUE, 'DELIVERED', NULL, 3),
    (5, '2025-01-05 16:20:00', FALSE, 'CANCELLED', 3, NULL);

-- Ajout de lignes de commande
INSERT INTO order_lines (id, quantity, order_id, article_id)
VALUES
    (1, 2, 1, 1),
    (2, 5, 2, 2),
    (3, 3, 3, 3),
    (4, 1, 4, 4),
    (5, 6, 5, 5),
    (6, 10, 1, 6),
    (7, 15, 2, 7),
    (8, 4, 3, 8),
    (9, 2, 4, 9),
    (10, 20, 5, 10);
