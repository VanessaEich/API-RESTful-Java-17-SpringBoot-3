CREATE TABLE IF NOT EXISTS `book` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `author` varchar(80),
                         `launch_date` datetime(6) NOT NULL,
                         `price` decimal(65,2) NOT NULL,
                         `title` varchar(255),
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;