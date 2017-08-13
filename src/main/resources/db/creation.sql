CREATE DATABASE rozetkadb CHARACTER SET utf8;

USE rozetkadb;

CREATE TABLE phones (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
)
DEFAULT CHARSET=utf8;