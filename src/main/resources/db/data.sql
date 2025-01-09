-- Ajout de familles (spécifiques aux vins)
INSERT INTO families (name) VALUES
                                ('Rouge'),
                                ('Rosé'),
                                ('Blanc'),
                                ('Pétillants'),
                                ('Digestifs');

-- Ajout d'articles (beaucoup plus d'articles répartis entre les familles de vins)
INSERT INTO articles (name, description, stock, stock_min, unit_price, family_id)
VALUES
    ('Château Margaux', 'Vin rouge prestigieux de Bordeaux', 50, 10, 249.99, 1),
    ('Bordeaux Supérieur', 'Vin rouge élégant', 60, 15, 19.99, 1),
    ('Côtes du Rhône', 'Vin rouge sec et structuré', 80, 20, 14.99, 1),
    ('Tavel', 'Vin rosé délicat de Provence', 40, 10, 11.99, 2),
    ('Rosé d’Anjou', 'Vin rosé léger et fruité', 70, 15, 9.50, 2),
    ('Sancerre Blanc', 'Vin blanc minéral et élégant', 50, 10, 24.99, 3),
    ('Chablis', 'Vin blanc sec et raffiné', 40, 8, 21.99, 3),
    ('Champagne Brut', 'Champagne pétillant classique', 30, 5, 49.99, 4),
    ('Prosecco', 'Vin pétillant italien', 70, 20, 12.99, 4),
    ('Crémant de Loire', 'Alternative pétillante abordable', 50, 15, 14.99, 4),
    ('Cognac VSOP', 'Digestif raffiné', 20, 5, 39.99, 5),
    ('Armagnac', 'Eau-de-vie traditionnelle française', 15, 3, 44.99, 5),
    ('Pineau des Charentes', 'Apéritif doux et fruité', 25, 5, 18.99, 5),
    ('Porto Rouge', 'Vin doux pour dessert', 30, 10, 15.50, 5),
    ('Grand Marnier', 'Liqueur à base de cognac et d’oranges', 25, 5, 32.99, 5),
    ('Syrah', 'Vin rouge intense', 25, 5, 22.99, 1),
    ('Muscadet', 'Vin blanc sec de Loire', 30, 5, 14.99, 3),
    ('Merlot', 'Vin rouge doux et fruité', 40, 10, 12.99, 1),
    ('Champagne Rosé', 'Champagne élégant', 15, 2, 65.99, 4),
    ('Porto Blanc', 'Digestif doux et rafraîchissant', 20, 3, 17.99, 5);

-- Ajout de clients
INSERT INTO customers (first_name, last_name, email, phone_number, address)
VALUES
    ('Jean', 'Dupont', 'jean.dupont@example.com', '0102030405', '1 Rue des Vins'),
    ('Marie', 'Curie', 'marie.curie@example.com', '0607080910', '2 Rue des Caves'),
    ('Albert', 'Einstein', 'albert.einstein@example.com', '0203040506', '3 Avenue Relativité'),
    ('Ada', 'Lovelace', 'ada.lovelace@example.com', '0304050607', '4 Place de la Programmation'),
    ('Grace', 'Hopper', 'grace.hopper@example.com', '0405060708', '5 Boulevard Informatique'),
    ('Charles', 'Darwin', 'charles.darwin@example.com', '0506070809', '6 Parc Naturel'),
    ('Isaac', 'Newton', 'isaac.newton@example.com', '0708091011', '7 Rue Gravité'),
    ('Leonardo', 'da Vinci', 'leonardo.davinci@example.com', '0809101112', '8 Rue Renaissance');

-- Ajout de fournisseurs
INSERT INTO suppliers (supplier_name, phone_number, email, address)
VALUES
    ('Fournisseur A', '0708091011', 'fournisseurA@example.com', '5 Rue Fournisseur'),
    ('Fournisseur B', '0809101112', 'fournisseurB@example.com', '6 Rue Fournisseur'),
    ('Fournisseur C', '0909111213', 'fournisseurC@example.com', '7 Rue Importateur'),
    ('Fournisseur D', '1011121314', 'fournisseurD@example.com', '8 Rue Exportateur'),
    ('Fournisseur E', '1112131415', 'fournisseurE@example.com', '9 Rue International');

-- Ajout de commandes (avec un mélange de commandes client et fournisseur)
INSERT INTO orders (order_date, is_supplier_order, status, customer_id, supplier_id)
VALUES
    ('2025-01-01 10:00:00', FALSE, 'CONFIRMED', 1, NULL),
    ('2025-01-02 11:30:00', TRUE, 'PENDING', NULL, 2),
    ('2025-01-03 14:15:00', FALSE, 'SHIPPED', 2, NULL),
    ('2025-01-04 09:45:00', TRUE, 'DELIVERED', NULL, 3),
    ('2025-01-05 16:20:00', FALSE, 'CANCELLED', 3, NULL),
    ('2025-01-06 10:00:00', FALSE, 'CONFIRMED', 4, NULL),
    ('2025-01-07 15:30:00', TRUE, 'DELIVERED', NULL, 1),
    ('2025-01-08 12:00:00', FALSE, 'PENDING', 5, NULL),
    ('2025-01-09 18:30:00', TRUE, 'SHIPPED', NULL, 4);

-- Ajout de lignes de commande
INSERT INTO order_lines (quantity, order_id, article_id)
VALUES
    (2, 1, 1),
    (5, 1, 2),
    (1, 2, 8),
    (10, 2, 9),
    (3, 3, 6),
    (4, 3, 5),
    (6, 4, 10),
    (12, 4, 11),
    (8, 5, 3),
    (15, 6, 4),
    (9, 7, 7),
    (7, 8, 13),
    (2, 9, 14),
    (3, 9, 15),
    (5, 6, 12);

-- Ajout d'un utilisateur admin pour les tests
INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$0/j.yNem1SkV.cSeTc4V9eDwSsbXxXb76jMEedvPyWBacg1JYJGt6', 'ADMIN');
-- Mot de passe : "admin123"
-- Pour ajouter des nouveaux utilisateurs utiliser le fichier BCryptTest.java dans dossier test et créer les mots de passes hasher

