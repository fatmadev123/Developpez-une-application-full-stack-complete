CREATE TABLE `USERS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `user_name` VARCHAR(50),
  `email` VARCHAR(50),
  `password` VARCHAR(255),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `TOPICS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(50),
  `description` VARCHAR(200)
);

CREATE TABLE `POSTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(50),
  `date` TIMESTAMP,
  `content` VARCHAR(5000),
  `user_id` INT,
  `topic_id` INT
);
ALTER TABLE `POSTS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS`(`id`);
ALTER TABLE `POSTS` ADD FOREIGN KEY (`topic_id`) REFERENCES `TOPICS` (`id`);

CREATE TABLE `COMMENTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `date` TIMESTAMP,
  `comment` VARCHAR(2000),
  `user_id` INT,
  `post_id` INT
);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`post_id`) REFERENCES `POSTS` (`id`);

CREATE TABLE `SUBSCRIPTIONS` (
  `user_id` INT, 
  `topic_id` INT
);
ALTER TABLE `SUBSCRIPTIONS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `SUBSCRIPTIONS` ADD FOREIGN KEY (`topic_id`) REFERENCES `TOPICS` (`id`);

INSERT INTO `USERS` (user_name, email, password, created_at, updated_at)
VALUES ('Jack', 'jack-harta@mdd.com', '$2a$10$g/DElCTYFKj4gsjcovH/auL02MjiCf6nxj7upc1mnx5cQJewoP6vG', '2024-03-29 14:30:15', '2024-03-29 14:30:15'),
	   ('Mike', 'mike-rawsoft@mdd.com', '$2a$10$g/DElCTYFKj4gsjcovH/auL02MjiCf6nxj7upc1mnx5cQJewoP6vG', '2024-01-29 14:30:15', '2024-01-29 14:30:15'),
       ('JCVD', 'jcvd@mdd.com', '$2a$10$g/DElCTYFKj4gsjcovH/auL02MjiCf6nxj7upc1mnx5cQJewoP6vG', '2024-04-29 14:30:15', '2024-04-29 14:30:15');

INSERT INTO `TOPICS` (name, description)
VALUES ('Java', 'Programmation en langage Java'),
       ('C#', 'Programmation en langage C#'),
       ('CSS', 'Style et mise en page'),
       ('JavaScript', 'Programmation en langage JavaScript');

INSERT INTO POSTS (title, date, content, user_id, topic_id)
VALUES ('La demande en développeur Java reste forte', '2024-03-29 14:30:15', 'Dans une récente étude, la majorité des personnes interrogées ont déclaré que leurs entreprises prévoyaient de recruter des développeurs Java cette année. Par contre, un peu moins de la moitié d\'entre elles prévoient d\'augmenter leur budget d’outillage. Tels sont les résultats du rapport 2024 Java Productivity Report publié la semaine dernière par le fournisseur d\'outils de développement Perforce Software.

Globalement, l\'enquête a révélé que les investissements dans les outils et les compétences Java étaient en hausse.', 1, 1),
       ('Comprendre .NET Standard en 5 min', '2024-01-29 14:30:15', 'Les technologies Microsoft adressent un grand nombre de plateformes différentes allant de systèmes d’exploitation comme Windows à des appareils mobiles comme les tablettes. D’autres parts, depuis quelques années, Satya Nadella a impulsé une “ouverture” des technologies Microsoft vers d’autres plateformes que Windows. Cette ouverture a encore augmenté le nombre de plateformes sur lesquelles des technologies pouvaient s’exécuter: Linux, macOS, iOS, Android etc… Toutes ces plateformes ont des spécificités qui nécessitent, pour chacune d’entre elles, une implémentation particulière.

', 2, 2),
       ('JDBC', '2024-04-29 14:30:15', 'JDBC est une marque déposée de Sun/Oracle, souvent considéré comme étant l\'acronyme de Java DataBase Connectivity et désigne une API de bas niveau de Java SE pour permettre un accès aux bases de données avec Java.

Elle permet de se connecter à une base de données et d\'interagir avec notamment en exécutant des requêtes SQL.

L\'architecture de JDBC permet d\'utiliser la même API pour accéder aux différentes bases de données grâce à l\'utilisation de pilotes (drivers) qui fournissent une implémentation spécifique à la base de données à utiliser. Chaque base de données a alors la responsabilité de fournir un pilote qui assure l\'interface entre l\'API et les actions exécutées de manière propriétaire sur la base de données.', 3, 1);

INSERT INTO COMMENTS (date, comment, user_id, post_id)
VALUES ('2024-03-29 14:30:15', 'Si vous avez des suggestions, n\'hésitez pas !', 1, 1),
       ('2024-05-29 14:30:15', 'Merci !', 2, 1),
       ('2024-01-29 14:30:15', 'De bonnes informations !', 1, 2),
       ('2024-04-29 14:30:15', 'I\'m aware!', 3, 3);

INSERT INTO SUBSCRIPTIONS (topic_id, user_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (1, 3);