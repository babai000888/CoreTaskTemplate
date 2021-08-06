DROP DATABASE IF EXISTS `CoreTaskTemplate`;
CREATE DATABASE IF NOT EXISTS `CoreTaskTemplate` DEFAULT CHARACTER SET utf8;

# Таблица Users.
DROP TABLE IF EXISTS `CoreTaskTemplate`.`Users`;
CREATE TABLE IF NOT EXISTS `CoreTaskTemplate`.`Users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci',
  `lastName` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci',
  `age` SMALLINT,
  PRIMARY KEY (`id`));
/*CREATE TABLE IF NOT EXISTS `CoreTaskTemplate`.`Users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` CHAR CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `lastName` CHAR CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  `age` SMALLINT NULL,
  PRIMARY KEY (`id`));  */

# Добавляем тестовых юзеров.
INSERT INTO `CoreTaskTemplate`.`Users` (`id`, `name`, `lastName`, `age`) VALUES (default, 'Мартин', 'Иден', 30);



