/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Host           : localhost:3306
 Source Schema         : shop
*/

drop database if exists shop;
create database shop charset utf8;
use shop;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
BEGIN;
INSERT INTO `admins` VALUES (1, 'admin', 'tuShOfiBrA8+br7ENrMS8A==');
INSERT INTO `admins` VALUES (2, 'mary', 'yJOXpxCuYkm+zIQrRHPdpQ==');
INSERT INTO `admins` VALUES (3, 'Anna', 'yJOXpxCuYkm+zIQrRHPdpQ==');
COMMIT;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT 'Names',
  `cover` varchar(255) DEFAULT NULL COMMENT 'Cover',
  `price` int(11) DEFAULT NULL COMMENT 'Price',
  `intro` varchar(255) DEFAULT NULL COMMENT 'Description',
  `type_id` int(11) DEFAULT NULL COMMENT 'Types',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES (1, '1', '../upload/011.jpg', 186, 'Description', 1);
INSERT INTO `goods` VALUES (2, '2', '../upload/012.jpg', 199, 'Description', 1);
INSERT INTO `goods` VALUES (3, '3', '../upload/013.jpg', 125, 'Description', 1);
INSERT INTO `goods` VALUES (4, '4', '../upload/014.jpg', 136, 'Description', 2);
INSERT INTO `goods` VALUES (5, '5', '../upload/015.jpg', 185, 'Description', 2);
INSERT INTO `goods` VALUES (6, '6', '../upload/016.jpg', 114, 'Description', 3);
INSERT INTO `goods` VALUES (7, '7', '../upload/017.jpg', 135, 'Description', 3);
INSERT INTO `goods` VALUES (8, '8', '../upload/018.jpg', 166, 'Description', 4);
INSERT INTO `goods` VALUES (9, '9', '../upload/019.jpg', 202, 'Description', 4);
INSERT INTO `goods` VALUES (10, '10', '../upload/021.jpg', 226, 'Description', 5);
INSERT INTO `goods` VALUES (11, '11', '../upload/022.jpg', 365, 'Description', 5);

COMMIT;

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` int(11) DEFAULT NULL COMMENT 'Price',
  `amount` int(11) DEFAULT NULL COMMENT 'Quantity',
  `order_id` int(11) DEFAULT NULL COMMENT 'Order id',
  `good_id` int(11) DEFAULT NULL COMMENT 'Product id',
  `color_id` int(11) DEFAULT NULL COMMENT 'Colour id',
  `size_id` int(11) DEFAULT NULL COMMENT 'Size id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` int(11) DEFAULT NULL COMMENT 'Total amount',
  `amount` int(11) DEFAULT NULL COMMENT 'Total number of product',
  `status` tinyint(4) DEFAULT '1' COMMENT 'Order status(1. Unpaid/2. Paid/3. Shipping/4. Completed)',
  `paytype` tinyint(4) DEFAULT '0' COMMENT 'Payment (1. Paypal/2. Credit card)',
  `name` varchar(255) DEFAULT NULL COMMENT 'Client',
  `phone` varchar(255) DEFAULT NULL COMMENT 'phone',
  `address` varchar(255) DEFAULT NULL COMMENT 'address',
  `systime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'order date',
  `user_id` int(11) DEFAULT NULL COMMENT 'username',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for shopcart
-- ----------------------------
DROP TABLE IF EXISTS `shopcart`;
CREATE TABLE `shopcart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT NULL COMMENT,
  `good_id` int(11) DEFAULT NULL,
  `color_id` int(11) DEFAULT NULL,
  `size_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sku_color
-- ----------------------------
DROP TABLE IF EXISTS `sku_color`;
CREATE TABLE `sku_color` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sku_color
-- ----------------------------
BEGIN;
INSERT INTO `sku_color` VALUES (1, 'Black');
INSERT INTO `sku_color` VALUES (2, 'White');
INSERT INTO `sku_color` VALUES (3, 'Blue');
INSERT INTO `sku_color` VALUES (4, 'Gery');
INSERT INTO `sku_color` VALUES (5, 'Yellow');
INSERT INTO `sku_color` VALUES (6, 'Orange');
INSERT INTO `sku_color` VALUES (7, 'Red');
INSERT INTO `sku_color` VALUES (8, 'Pink');
INSERT INTO `sku_color` VALUES (9, 'Green');
INSERT INTO `sku_color` VALUES (10, 'Brown');
COMMIT;

-- ----------------------------
-- Table structure for sku_good
-- ----------------------------
DROP TABLE IF EXISTS `sku_good`;
CREATE TABLE `sku_good` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) DEFAULT '0' COMMENT 'Stock',
  `color_id` int(11) DEFAULT NULL,
  `size_id` int(11) DEFAULT NULL,
  `good_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sku_size
-- ----------------------------
DROP TABLE IF EXISTS `sku_size`;
CREATE TABLE `sku_size` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sku_size
-- ----------------------------
BEGIN;
INSERT INTO `sku_size` VALUES (1, 'S');
INSERT INTO `sku_size` VALUES (2, 'M');
INSERT INTO `sku_size` VALUES (3, 'L');
INSERT INTO `sku_size` VALUES (4, 'XL');
INSERT INTO `sku_size` VALUES (5, 'XXL');
INSERT INTO `sku_size` VALUES (6, '3XL');
INSERT INTO `sku_size` VALUES (7, '4XL');
INSERT INTO `sku_size` VALUES (8, '5XL');
INSERT INTO `sku_size` VALUES (9, '36');
INSERT INTO `sku_size` VALUES (10, '37');
INSERT INTO `sku_size` VALUES (11, '38');
INSERT INTO `sku_size` VALUES (12, '39');
INSERT INTO `sku_size` VALUES (13, '40');
INSERT INTO `sku_size` VALUES (14, '41');
INSERT INTO `sku_size` VALUES (15, '42');
INSERT INTO `sku_size` VALUES (16, '43');
INSERT INTO `sku_size` VALUES (17, '44');
INSERT INTO `sku_size` VALUES (18, '45');
COMMIT;

-- ----------------------------
-- Table structure for tops
-- ----------------------------
DROP TABLE IF EXISTS `tops`;
CREATE TABLE `tops` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) DEFAULT NULL COMMENT 'Top',
  `good_id` int(11) DEFAULT NULL COMMENT ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='Home Recommended Products';


-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT 'Name',
  `cover` varchar(255) DEFAULT NULL COMMENT 'Cover',
  `num` int(11) DEFAULT '0' COMMENT 'Sort number (from small to large)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of types
-- ----------------------------
BEGIN;
INSERT INTO `types` VALUES (1, 'Women`s', '../upload/13.jpg', 3);
INSERT INTO `types` VALUES (2, 'Men', '../upload/1.jpg', 4);
INSERT INTO `types` VALUES (3, 'Kid', '../upload/25.jpg', 5);
INSERT INTO `types` VALUES (4, 'Food', '../upload/38.jpg', 2);
INSERT INTO `types` VALUES (5, 'Shoes', '../upload/50.jpg', 1);
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT,
  `password` varchar(255) DEFAULT NULL COMMENT,
  `name` varchar(255) DEFAULT NULL COMMENT,
  `phone` varchar(255) DEFAULT NULL COMMENT,
  `address` varchar(255) DEFAULT NULL COMMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;