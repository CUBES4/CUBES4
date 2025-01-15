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
    ('Château Margaux', 'Vin rouge prestigieux de Bordeaux', 8, 10, 249.99, 1), -- En dessous du stock min
    ('Bordeaux Supérieur', 'Vin rouge élégant', 12, 15, 19.99, 1), -- En dessous du stock min
    ('Côtes du Rhône', 'Vin rouge sec et structuré', 25, 20, 14.99, 1),
    ('Tavel', 'Vin rosé délicat de Provence', 5, 10, 11.99, 2), -- En dessous du stock min
    ('Rosé d’Anjou', 'Vin rosé léger et fruité', 70, 15, 9.50, 2),
    ('Sancerre Blanc', 'Vin blanc minéral et élégant', 8, 10, 24.99, 3), -- En dessous du stock min
    ('Chablis', 'Vin blanc sec et raffiné', 40, 8, 21.99, 3),
    ('Champagne Brut', 'Champagne pétillant classique', 15, 5, 49.99, 4),
    ('Prosecco', 'Vin pétillant italien', 50, 20, 12.99, 4),
    ('Crémant de Loire', 'Alternative pétillante abordable', 50, 15, 14.99, 4),
    ('Cognac VSOP', 'Digestif raffiné', 10, 5, 39.99, 5),
    ('Armagnac', 'Eau-de-vie traditionnelle française', 15, 3, 44.99, 5),
    ('Pineau des Charentes', 'Apéritif doux et fruité', 5, 5, 18.99, 5), -- Juste au stock min
    ('Porto Rouge', 'Vin doux pour dessert', 30, 10, 15.50, 5),
    ('Grand Marnier', 'Liqueur à base de cognac et d’oranges', 22, 5, 32.99, 5),
    ('Syrah', 'Vin rouge intense', 15, 5, 22.99, 1),
    ('Muscadet', 'Vin blanc sec de Loire', 30, 5, 14.99, 3),
    ('Merlot', 'Vin rouge doux et fruité', 18, 10, 12.99, 1), -- En dessous du stock min
    ('Champagne Rosé', 'Champagne élégant', 10, 2, 65.99, 4),
    ('Porto Blanc', 'Digestif doux et rafraîchissant', 20, 3, 17.99, 5),

    -- Nouveaux articles ajoutés
    ('Gewurztraminer', 'Vin blanc moelleux d’Alsace', 8, 10, 14.50, 3), -- En dessous du stock min
    ('Riesling', 'Vin blanc sec d’Alsace', 25, 10, 18.75, 3),
    ('Beaujolais Nouveau', 'Vin rouge léger de Bourgogne', 35, 20, 10.99, 1),
    ('Cava', 'Vin pétillant espagnol', 60, 15, 9.99, 4),
    ('Sherry', 'Vin fortifié espagnol', 10, 5, 19.50, 5),
    ('Marsala', 'Vin de dessert italien', 12, 8, 21.00, 5),
    ('Barolo', 'Vin rouge italien de caractère', 15, 7, 52.50, 1),
    ('Chianti', 'Vin rouge sec italien', 30, 10, 22.99, 1),
    ('Zinfandel', 'Vin rouge californien riche', 20, 10, 29.99, 1),
    ('Pinot Grigio', 'Vin blanc sec italien', 25, 10, 17.99, 3),
    ('Tokaji', 'Vin de dessert hongrois', 12, 5, 42.99, 5);


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

