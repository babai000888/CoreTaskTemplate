DROP DATABASE IF EXISTS `CoreTaskTemplate`;
CREATE DATABASE IF NOT EXISTS `CoreTaskTemplate` DEFAULT CHARACTER SET utf8 ;;

# Таблица Users.
DROP TABLE IF EXISTS `CoreTaskTemplate`.`Users`;
CREATE TABLE  `CoreTaskTemplate`.`Users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci',
  `lastName` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci',
  `age` SMALLINT,
  PRIMARY KEY (`id`));

# Добавля юзера.
INSERT INTO `CoreTaskTemplate`.`Users` (`id`, `name`, `lastName`, `age`) VALUES (default, 'Мартин', 'Иден', 30);

# Удалить юзера
DELETE FROM `CoreTaskTemplate`.`Users` WHERE id = (1);

# Получить всех юзеров.
SELECT * FROM `CoreTaskTemplate`.`Users`;

# Очистить таблицу
TRUNCATE TABLE `CoreTaskTemplate`.`Users`;


