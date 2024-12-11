# Configuration de la Base de Données en Production & Environnement de Développement

## Base de Données en Production (MariaDB)

### Prérequis

- Docker installé sur votre machine.

### Étapes pour Lancer un Conteneur MariaDB

1. Lancer un conteneur mariaDB
   ```bash
    docker run --name cubes-mariadb \
    -e MARIADB_ROOT_PASSWORD=rootpassword \
    -e MARIADB_DATABASE=cubes4 \
    -e MARIADB_USER=root \
    -e MARIADB_PASSWORD=root \
    -p 3306:3306 \
    -d mariadb:10.6
    ```

Explications:

* MARIADB_ROOT_PASSWORD: mot de passe root pour MariaDB.
* MARIADB_DATABASE=cubes4: crée une base cubes4 par défaut.
* MARIADB_USER et MARIADB_PASSWORD: utilisateur non-root pour les connexions applicatives.
* Le port 3306 est mappé sur l’hôte pour accès local.

2. Une fois le conteneur en marche, vous pouvez exécuter le fichier schema.sql:
    ```bash
    docker exec -i cubes-mariadb mysql -u root -p"password" cubes4 < schema.sql
    ```
   Ceci créera la structure des tables dans la base de données cubes4.

#### Utilisation en Production

* Dans le fichier application.properties de l’application Spring Boot, renseignez les informations de connexion à
  MariaDB (voir section plus bas).

* Démarrez votre application, elle se connectera à MariaDB et utilisera la base cubes4.

## Base de Données H2 pour les Tests / Développement

H2 est une base de données en mémoire très pratique pour le développement et les tests. Elle ne nécessite aucune
installation supplémentaire. Les données sont temporaires et disparaissent quand l’application s’arrête.

### Pourquoi H2 ?

* Permet de tester rapidement sans déployer MariaDB.
* Chargement rapide des données, parfait pour les tests unitaires et d’intégration.
* Console web intégrée (/h2-console) pour visualiser les données pendant le dev.

### Comment Utiliser H2 ?

En mode développement/test, il suffit d’utiliser un fichier de configuration dédié (application-dev.properties) qui
pointe vers H2. Quand vous lancez l’application avec le profil dev, Spring Boot chargera automatiquement cette
configuration.

Par exemple, pour lancer l’application avec le profil dev :

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

Ensuite, vous pouvez accéder à la console H2 sur :

```bash
http://localhost:8080/h2-console
```

Ou directement à partir d'intelliJ.
Le driver sera org.h2.Driver et l’URL jdbc:h2:mem:cubes4.

## Fichiers de Configuration Spring Boot

Vous aurez deux fichiers de propriétés principaux :

### application.properties (Production, MariaDB)

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/cubes4
spring.datasource.username=cubes4user
spring.datasource.password=cubes4password
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
```

Ici :

* ddl-auto=none, car la structure est déjà créée via schema.sql. En production, on évite les modifications automatiques
  du schéma.

### application-dev.properties (Développement, H2)

```properties
spring.datasource.url=jdbc:h2:mem:cubes4;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Ici :

* ddl-auto=update permet d’adapter automatiquement le schéma pour le dev.
* show-sql=true permet de voir les requêtes dans la console.
* h2-console.enabled=true active la console web H2.

#### Avec cette configuration, vous pouvez :

* Développer et tester localement avec H2 en utilisant le profil dev.
* Passer en production sur une base MariaDB persistante simplement en changeant le profil ou en déployant l’application
  avec les paramètres de prod.
* Le fichier database.md explique clairement comment lancer et initialiser la base sous Docker, facilitant le travail
  d’équipe.