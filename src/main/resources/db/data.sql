-- Ajout de familles (spécifiques aux vins)
INSERT INTO families (name) VALUES
                                ('Rouge'),
                                ('Rosé'),
                                ('Blanc'),
                                ('Pétillants'),
                                ('Digestifs');

-- Ajout d'articles (beaucoup plus d'articles répartis entre les familles de vins)
INSERT INTO articles (id, name, description, stock, stock_min, unit_price, family_id)
VALUES
    (1, 'Château Margaux', 'Vin rouge prestigieux de Bordeaux', 50, 10, 249.99, 1),
    (2, 'Bordeaux Supérieur', 'Vin rouge élégant', 60, 15, 19.99, 1),
    (3, 'Côtes du Rhône', 'Vin rouge sec et structuré', 80, 20, 14.99, 1),
    (4, 'Tavel', 'Vin rosé délicat de Provence', 40, 10, 11.99, 2),
    (5, 'Rosé d’Anjou', 'Vin rosé léger et fruité', 70, 15, 9.50, 2),
    (6, 'Sancerre Blanc', 'Vin blanc minéral et élégant', 50, 10, 24.99, 3),
    (7, 'Chablis', 'Vin blanc sec et raffiné', 40, 8, 21.99, 3),
    (8, 'Champagne Brut', 'Champagne pétillant classique', 30, 5, 49.99, 4),
    (9, 'Prosecco', 'Vin pétillant italien', 70, 20, 12.99, 4),
    (10, 'Crémant de Loire', 'Alternative pétillante abordable', 50, 15, 14.99, 4),
    (11, 'Cognac VSOP', 'Digestif raffiné', 20, 5, 39.99, 5),
    (12, 'Armagnac', 'Eau-de-vie traditionnelle française', 15, 3, 44.99, 5),
    (13, 'Pineau des Charentes', 'Apéritif doux et fruité', 25, 5, 18.99, 5),
    (14, 'Porto Rouge', 'Vin doux pour dessert', 30, 10, 15.50, 5),
    (15, 'Grand Marnier', 'Liqueur à base de cognac et d’oranges', 25, 5, 32.99, 5);

-- Ajout de clients
INSERT INTO customers (id, first_name, last_name, email, phone_number, address)
VALUES
    (1, 'Jean', 'Dupont', 'jean.dupont@example.com', '0102030405', '1 Rue des Vins'),
    (2, 'Marie', 'Curie', 'marie.curie@example.com', '0607080910', '2 Rue des Caves'),
    (3, 'Albert', 'Einstein', 'albert.einstein@example.com', '0203040506', '3 Avenue Relativité'),
    (4, 'Ada', 'Lovelace', 'ada.lovelace@example.com', '0304050607', '4 Place de la Programmation'),
    (5, 'Grace', 'Hopper', 'grace.hopper@example.com', '0405060708', '5 Boulevard Informatique'),
    (6, 'Charles', 'Darwin', 'charles.darwin@example.com', '0506070809', '6 Parc Naturel'),
    (7, 'Isaac', 'Newton', 'isaac.newton@example.com', '0708091011', '7 Rue Gravité'),
    (8, 'Leonardo', 'da Vinci', 'leonardo.davinci@example.com', '0809101112', '8 Rue Renaissance');

-- Ajout de fournisseurs
INSERT INTO suppliers (id, supplier_name, phone_number, email, address)
VALUES
    (1, 'Fournisseur A', '0708091011', 'fournisseurA@example.com', '5 Rue Fournisseur'),
    (2, 'Fournisseur B', '0809101112', 'fournisseurB@example.com', '6 Rue Fournisseur'),
    (3, 'Fournisseur C', '0909111213', 'fournisseurC@example.com', '7 Rue Importateur'),
    (4, 'Fournisseur D', '1011121314', 'fournisseurD@example.com', '8 Rue Exportateur'),
    (5, 'Fournisseur E', '1112131415', 'fournisseurE@example.com', '9 Rue International');

-- Ajout de commandes (avec un mélange de commandes client et fournisseur)
INSERT INTO orders (id, order_date, is_supplier_order, status, customer_id, supplier_id)
VALUES
    (1, '2025-01-01 10:00:00', FALSE, 'CONFIRMED', 1, NULL),
    (2, '2025-01-02 11:30:00', TRUE, 'PENDING', NULL, 2),
    (3, '2025-01-03 14:15:00', FALSE, 'SHIPPED', 2, NULL),
    (4, '2025-01-04 09:45:00', TRUE, 'DELIVERED', NULL, 3),
    (5, '2025-01-05 16:20:00', FALSE, 'CANCELLED', 3, NULL),
    (6, '2025-01-06 10:00:00', FALSE, 'CONFIRMED', 4, NULL),
    (7, '2025-01-07 15:30:00', TRUE, 'DELIVERED', NULL, 1),
    (8, '2025-01-08 12:00:00', FALSE, 'PENDING', 5, NULL),
    (9, '2025-01-09 18:30:00', TRUE, 'SHIPPED', NULL, 4);

-- Ajout de lignes de commande
INSERT INTO order_lines (id, quantity, order_id, article_id)
VALUES
    (1, 2, 1, 1),
    (2, 5, 1, 2),
    (3, 1, 2, 8),
    (4, 10, 2, 9),
    (5, 3, 3, 6),
    (6, 4, 3, 5),
    (7, 6, 4, 10),
    (8, 12, 4, 11),
    (9, 8, 5, 3),
    (10, 15, 6, 4),
    (11, 9, 7, 7),
    (12, 7, 8, 13),
    (13, 2, 9, 14),
    (14, 3, 9, 15),
    (15, 5, 6, 12);

-- Ajout de données supplémentaires pour divers scénarios
INSERT INTO articles (id, name, description, stock, stock_min, unit_price, family_id)
VALUES
    (16, 'Syrah', 'Vin rouge intense', 25, 5, 22.99, 1),
    (17, 'Muscadet', 'Vin blanc sec de Loire', 30, 5, 14.99, 3),
    (18, 'Merlot', 'Vin rouge doux et fruité', 40, 10, 12.99, 1),
    (19, 'Champagne Rosé', 'Champagne élégant', 15, 2, 65.99, 4),
    (20, 'Porto Blanc', 'Digestif doux et rafraîchissant', 20, 3, 17.99, 5);
