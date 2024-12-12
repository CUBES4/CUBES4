-- Use an English-based schema:
CREATE DATABASE IF NOT EXISTS cubes4 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cubes4;

DROP TABLE IF EXISTS order_lines;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS supplier_article;
DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS families;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS customers;

-- Families table
CREATE TABLE families
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
) ENGINE = InnoDB;

-- Articles Table: Represents products with links to a Famille
CREATE TABLE articles
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    description  TEXT,
    unit_price   DOUBLE PRECISION,
    stock        INT DEFAULT 0,
    stock_min    INT DEFAULT 0,
    family_id    INT,
    CONSTRAINT fk_article_family FOREIGN KEY (family_id) REFERENCES families (id)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB;

-- Suppliers Table: Represents suppliers
CREATE TABLE suppliers
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    email   VARCHAR(255),
    phone   VARCHAR(50)
) ENGINE = InnoDB;

-- Supplier-Article join table (Many-to-Many)
CREATE TABLE supplier_article
(
    supplier_id INT NOT NULL,
    article_id  INT NOT NULL,
    PRIMARY KEY (supplier_id, article_id),
    CONSTRAINT fk_supplier_article_supplier FOREIGN KEY (supplier_id) REFERENCES suppliers (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_supplier_article_article FOREIGN KEY (article_id) REFERENCES articles (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- Customers table
CREATE TABLE customers
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255),
    address    VARCHAR(255),
    phone      VARCHAR(50)
) ENGINE = InnoDB;

-- Orders Table: Represents both customers and supplier orders
-- If is_supplier_order = false, customer_id should be set
-- If is_supplier_order = true, supplier_id should be set
CREATE TABLE orders
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    order_date        DATETIME,
    is_supplier_order BOOLEAN NOT NULL DEFAULT FALSE,
    status            VARCHAR(50),
    customer_id       INT,
    supplier_id       INT,
    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customers (id)
        ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_order_supplier FOREIGN KEY (supplier_id) REFERENCES suppliers (id)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB;

-- OrderLines table
CREATE TABLE order_lines
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    quantity   INT NOT NULL,
    order_id   INT NOT NULL,
    article_id INT NOT NULL,
    CONSTRAINT fk_orderline_order FOREIGN KEY (order_id) REFERENCES orders (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_orderline_article FOREIGN KEY (article_id) REFERENCES articles (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- Indexes (optional)
CREATE INDEX idx_article_family ON articles (family_id);
CREATE INDEX idx_order_customer ON orders (customer_id);
CREATE INDEX idx_order_supplier ON orders (supplier_id);
CREATE INDEX idx_orderline_order ON order_lines (order_id);
CREATE INDEX idx_orderline_article ON order_lines (article_id);
