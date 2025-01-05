-- Création des tables pour le projet CUBES4

-- Familles
CREATE TABLE FAMILIES (
                          ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                          NAME VARCHAR(255) NOT NULL
);

-- Articles
CREATE TABLE ARTICLES (
                          ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                          NAME VARCHAR(255) NOT NULL,
                          DESCRIPTION TEXT,
                          UNIT_PRICE FLOAT NOT NULL,
                          STOCK INTEGER,
                          STOCK_MIN INTEGER,
                          FAMILY_ID BIGINT,
                          CONSTRAINT FK_ARTICLE_FAMILY FOREIGN KEY (FAMILY_ID) REFERENCES FAMILIES (ID)
                              ON DELETE SET NULL
);

-- Clients
CREATE TABLE CUSTOMERS (
                           ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                           FIRST_NAME VARCHAR(255),
                           LAST_NAME VARCHAR(255) NOT NULL,
                           EMAIL VARCHAR(255),
                           ADDRESS VARCHAR(255),
                           PHONE_NUMBER VARCHAR(255)
);

-- Fournisseurs
CREATE TABLE SUPPLIERS (
                           ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                           SUPPLIER_NAME VARCHAR(255) NOT NULL,
                           ADDRESS VARCHAR(255),
                           EMAIL VARCHAR(255),
                           PHONE_NUMBER VARCHAR(255)
);

-- Association fournisseur-article
CREATE TABLE SUPPLIER_ARTICLE (
                                  SUPPLIER_ID BIGINT NOT NULL,
                                  ARTICLE_ID BIGINT NOT NULL,
                                  PRIMARY KEY (SUPPLIER_ID, ARTICLE_ID),
                                  CONSTRAINT FK_SUPPLIER_ARTICLE_SUPPLIER FOREIGN KEY (SUPPLIER_ID) REFERENCES SUPPLIERS (ID)
                                      ON DELETE CASCADE,
                                  CONSTRAINT FK_SUPPLIER_ARTICLE_ARTICLE FOREIGN KEY (ARTICLE_ID) REFERENCES ARTICLES (ID)
                                      ON DELETE CASCADE
);

-- Commandes
CREATE TABLE ORDERS (
                        ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                        ORDER_DATE TIMESTAMP,
                        IS_SUPPLIER_ORDER BOOLEAN NOT NULL DEFAULT FALSE,
                        STATUS ENUM('CANCELLED', 'CONFIRMED', 'DELIVERED', 'PENDING', 'SHIPPED'),
                        CUSTOMER_ID BIGINT,
                        SUPPLIER_ID BIGINT,
                        CONSTRAINT FK_ORDER_CUSTOMER FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS (ID)
                            ON DELETE SET NULL,
                        CONSTRAINT FK_ORDER_SUPPLIER FOREIGN KEY (SUPPLIER_ID) REFERENCES SUPPLIERS (ID)
                            ON DELETE SET NULL
);

-- Lignes de commande
CREATE TABLE ORDER_LINES (
                             ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                             QUANTITY INTEGER NOT NULL,
                             ORDER_ID BIGINT NOT NULL,
                             ARTICLE_ID BIGINT NOT NULL,
                             CONSTRAINT FK_ORDERLINE_ORDER FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ID)
                                 ON DELETE CASCADE,
                             CONSTRAINT FK_ORDERLINE_ARTICLE FOREIGN KEY (ARTICLE_ID) REFERENCES ARTICLES (ID)
                                 ON DELETE CASCADE
);
