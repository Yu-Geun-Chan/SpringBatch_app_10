# 개발 DB 생성

DROP DATABASE IF EXISTS SpringBatch_app_10__dev;
CREATE DATABASE SpringBatch_app_10__dev;
USE SpringBatch_app_10__dev;

DROP DATABASE IF EXISTS SpringBatch_app_10__test;
CREATE DATABASE SpringBatch_app_10__test;
USE SpringBatch_app_10__test;

SHOW TABLES;

SELECT * FROM `member`;
SELECT * FROM song;
SELECT * FROM product;