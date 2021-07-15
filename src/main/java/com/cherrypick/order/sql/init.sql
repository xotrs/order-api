CREATE TABLE `member`
(
    `id`        int(11) unsigned NOT NULL AUTO_INCREMENT,
    `email`     varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
    `name`      varchar(20) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '',
    `nick_name` varchar(30) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '',
    `password`  varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
    `phone`     varchar(20) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '',
    `gender`    varchar(1) COLLATE utf8mb4_unicode_ci            DEFAULT '',
    `role`      enum('USER','ARTIST') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'USER',
    PRIMARY KEY (`id`),
    KEY         `email` (`email`),
    KEY         `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `product`
(
    `id`    int(11) unsigned NOT NULL AUTO_INCREMENT,
    `title` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `product`
(
    `id`    int(11) unsigned NOT NULL AUTO_INCREMENT,
    `title` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `order`
(
    `id`            varchar(12) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '',
    `product_title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
    `product_id`    int(11) NOT NULL,
    `member_id`     int(11) NOT NULL,
    `created_at`    datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`),
    KEY             `member_id` (`member_id`),
    KEY             `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `token`
(
    `id`            int(11) unsigned NOT NULL AUTO_INCREMENT,
    `access_token`  varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `refresh_token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `member_id`     int(11) NOT NULL,
    PRIMARY KEY (`id`),
    KEY             `member_id` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO `product` (`id`, `title`)
VALUES
	(1, '입점3주년기념할인⭐ 인기짱 다홍에그타르트'),
	(2, '[프리저브드]기분좋은 블루수국 꽃다발⭐'),
	(3, '플라워 프린트 화이트 원피스'),
	(4, '실버 마스크목걸이 마스크스트랩 4종류 목걸이겸용');