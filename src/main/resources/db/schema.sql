-- Use or create the database (adjust as needed)
CREATE DATABASE IF NOT EXISTS cubes4 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cubes4;

-- Drop tables if they already exist (clean start)
DROP TABLE IF EXISTS ligne_commandes;
DROP TABLE IF EXISTS commandes;
DROP TABLE IF EXISTS fournisseur_article;
DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS familles;
DROP TABLE IF EXISTS fournisseurs;
DROP TABLE IF EXISTS clients;

-- Famille Table: Represents product families/categories
CREATE TABLE familles
(
    id  INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
) ENGINE = InnoDB;

-- Articles Table: Represents products with links to a Famille
CREATE TABLE articles
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    nom          VARCHAR(255) NOT NULL,
    description  TEXT,
    prixUnitaire DOUBLE PRECISION,
    prixCarton   DOUBLE PRECISION,
    stock        INT DEFAULT 0,
    stockMin     INT DEFAULT 0,
    famille_id   INT,
    CONSTRAINT fk_article_famille FOREIGN KEY (famille_id) REFERENCES familles (id)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB;

-- Fournisseurs Table: Represents suppliers
CREATE TABLE fournisseurs
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nom       VARCHAR(255) NOT NULL,
    email     VARCHAR(255),
    telephone VARCHAR(50),
    adresse   VARCHAR(255)
) ENGINE = InnoDB;

-- Join Table for Fournisseur and Article (Many-to-Many)
CREATE TABLE fournisseur_article
(
    fournisseur_id INT NOT NULL,
    article_id     INT NOT NULL,
    PRIMARY KEY (fournisseur_id, article_id),
    CONSTRAINT fk_fournisseur_article_fournisseur FOREIGN KEY (fournisseur_id) REFERENCES fournisseurs (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_fournisseur_article_article FOREIGN KEY (article_id) REFERENCES articles (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- Clients Table: Represents customers
CREATE TABLE clients
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nom       VARCHAR(255) NOT NULL,
    prenom    VARCHAR(255),
    email     VARCHAR(255),
    adresse   VARCHAR(255),
    telephone VARCHAR(50)
) ENGINE = InnoDB;

-- Commandes Table: Represents both client and supplier orders
-- If isFournisseurCommande = false, client_id should be set
-- If isFournisseurCommande = true, fournisseur_id should be set
-- en gros, si isFournisseurCommande = false, alors c'est une commande client, sinon commande fournisseur
CREATE TABLE commandes
(
    id                    INT AUTO_INCREMENT PRIMARY KEY,
    dateCommande          DATETIME,
    isFournisseurCommande BOOLEAN NOT NULL DEFAULT FALSE,
    statut                VARCHAR(50),
    client_id             INT,
    fournisseur_id        INT,
    CONSTRAINT fk_commande_client FOREIGN KEY (client_id) REFERENCES clients (id)
        ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_commande_fournisseur FOREIGN KEY (fournisseur_id) REFERENCES fournisseurs (id)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB;

-- LigneCommandes Table: Represents order lines linking a commande to its articles
CREATE TABLE ligne_commandes
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    quantite    INT NOT NULL,
    commande_id INT NOT NULL,
    article_id  INT NOT NULL,
    CONSTRAINT fk_lignecommande_commande FOREIGN KEY (commande_id) REFERENCES commandes (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_lignecommande_article FOREIGN KEY (article_id) REFERENCES articles (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- Optional: Create some indexes for performance (adjust as needed)
CREATE INDEX idx_article_famille ON articles (famille_id);
CREATE INDEX idx_commande_client ON commandes (client_id);
CREATE INDEX idx_commande_fournisseur ON commandes (fournisseur_id);
CREATE INDEX idx_lignecommande_commande ON ligne_commandes (commande_id);
CREATE INDEX idx_lignecommande_article ON ligne_commandes (article_id);
