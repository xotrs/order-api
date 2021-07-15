# order-application

## 개요

* 회원 인증 주문 및 주문 목록 API

## Development

```bash
# docker-compose 위치 이동
cd src/main/java/com/cherrypick/order/docker
# 도커 시작
docker-compose down -v && docker-compose up -d

# order 생성
CREATE DATABASE `order`;

# 테이블 생성
# 회원
CREATE TABLE `member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `nick_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `gender` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `role` enum('USER','ARTIST') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id`),
  KEY `email` (`email`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

# 상품
CREATE TABLE `product` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;  

# 주문
CREATE TABLE `order` (
  `id` varchar(12) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `product_title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `product_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `member_id` (`member_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

# 토큰
CREATE TABLE `token` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `access_token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `refresh_token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `member_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

# 상품 더미 데이터 삽입  
INSERT INTO `product` (`id`, `title`)
VALUES
	(1, '입점3주년기념할인⭐ 인기짱 다홍에그타르트'),
	(2, '[프리저브드]기분좋은 블루수국 꽃다발⭐'),
	(3, '플라워 프린트 화이트 원피스'),
	(4, '실버 마스크목걸이 마스크스트랩 4종류 목걸이겸용');


# 주문 정보는 API가 없어서 회원가입 후 상품 테이블의 id, 회원 테이블의 id 그리고 상품 제목을 같이 조합해서 데이터를 넣어주시면 감사하겠습니다.

# INSERT INTO `order` (`id`, `product_title`, `product_id`, `member_id`, `created_at`)
#VALUES
#	('123ACFE89C12', '실버 마스크목걸이 마스크스트랩 4종류 목걸이겸용', 4, 1, '2021-07-10 16:35:21'),
#	('12D4E6CE90Z3', '[프리저브드]기분좋은 블루수국 꽃다발💙', 2, 1, '2021-07-13 02:17:44'),
#	('12GX4X51GX1A', '플라워 프린트 화이트 원피스', 3, 1, '2021-07-13 16:30:56'),
#	('12GX4X51GX1F', '입점3주년기념할인⭐ 인기짱 다홍에그타르트', 1, 1, '2021-07-13 02:15:56');

spring-boot application 시작
```

## API
일반적인 API 흐름은 다음과 같습니다.

(회원가입) -> (로그인) -> API 통신(+로그인 시 발급받은 토큰)

1. 회원 가입
- 회원 가입을 하면 member에 회원 정보가 삽입됩니다.

2. 로그인
- 로그인을 하면 token 테이블에 해당 회원의 access_token, refresh_token이 생성됩니다.
- redis 같은 곳에서 토큰 관리 및 만료시간 관리에 유용할 것 같지만 임의로 RDB에 저장했습니다.

3. 로그아웃
- 로그아웃을 하면 해당 회원의 토큰 값이 token 테이블에서 삭제됩니다.

4. 재인증
- 토큰의 만료시간이 다되면 재인증 API를 통해서 토큰을 다시 발급받을 수 있습니다.

5. 여러 회원 목록 조회, 단일 회원 상세 정보 조회, 단일 회원의 주문 목록 조회
- 해당 API는 위에서 로그인 시 발급 받은 access_token을 통해서 통신을 할 수 있습니다.
- swagger의 Authorize 기능을 이용할 수 있으며, access_token을 입력할 때는 **prefix로 Bearer를 꼭 붙여주시면 감사하겠습니다.**
- 자세한 사항은 swagger를 통해서 확인하실 수 있습니다.

## swagger 주소
http://localhost:8080/swagger-ui.html


감사합니다.