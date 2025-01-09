-- Création des tables

CREATE TABLE families (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

CREATE TABLE articles (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description VARCHAR(255),
                          stock INT DEFAULT 0,
                          stock_min INT DEFAULT 0,
                          unit_price DOUBLE NOT NULL,
                          family_id BIGINT,
                          FOREIGN KEY (family_id) REFERENCES families(id)
);

CREATE TABLE customers (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           first_name VARCHAR(255) NOT NULL,
                           last_name VARCHAR(255) NOT NULL,
                           email VARCHAR(255),
                           phone_number VARCHAR(255),
                           address VARCHAR(255)
);

CREATE TABLE suppliers (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           supplier_name VARCHAR(255) NOT NULL,
                           phone_number VARCHAR(255),
                           email VARCHAR(255),
                           address VARCHAR(255)
);

CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        order_date TIMESTAMP NOT NULL,
                        is_supplier_order BOOLEAN NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        customer_id BIGINT,
                        supplier_id BIGINT,
                        FOREIGN KEY (customer_id) REFERENCES customers(id),
                        FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

CREATE TABLE order_lines (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             quantity INT NOT NULL,
                             order_id BIGINT,
                             article_id BIGINT,
                             FOREIGN KEY (order_id) REFERENCES orders(id),
                             FOREIGN KEY (article_id) REFERENCES articles(id)
);

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
);

