-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: kakao
-- ------------------------------------------------------
-- Server version	8.0.17

--
-- Dumping data for table `room`, `user`, `room_user`
--



LOCK TABLES `room` WRITE;
INSERT INTO kakao.room (id, created_at, name) VALUES ('AAA', 20210119012047, 'TestRoom1');
INSERT INTO kakao.room (id, created_at, name) VALUES ('BBB', 20210119012047, 'TestRoom2');
INSERT INTO kakao.room (id, created_at, name) VALUES ('CCC', 20210119012047, 'TestRoom3');
INSERT INTO kakao.room (id, created_at, name) VALUES ('DDD', 20210119012047, 'TestRoom4');
INSERT INTO kakao.room (id, created_at, name) VALUES ('EEE', 20210119012047, 'TestRoom5');
INSERT INTO kakao.room (id, created_at, name) VALUES ('FFF', 20210119012047, 'TestRoom6');
INSERT INTO kakao.room (id, created_at, name) VALUES ('GGG', 20210119012047, 'TestRoom7');
INSERT INTO kakao.room (id, created_at, name) VALUES ('HHH', 20210119012047, 'TestRoom8');
INSERT INTO kakao.room (id, created_at, name) VALUES ('III', 20210119012047, 'TestRoom9');
INSERT INTO kakao.room (id, created_at, name) VALUES ('JJJ', 20210119012047, 'TestRoom10');
UNLOCK TABLES;

LOCK TABLES `user` WRITE;
INSERT INTO kakao.user (id, created_at, name, password) VALUES (1, 20210119012048, 'TestUser1', '75601');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (2, 20210119012048, 'TestUser2', 'AP641');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (3, 20210119012048, 'TestUser3', 'N1111');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (4, 20210119012048, 'TestUser4', 'L6D91');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (5, 20210119012048, 'TestUser5', 'D5V91');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (6, 20210119012048, 'TestUser6', '4AD31');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (7, 20210119012048, 'TestUser7', '06B61');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (8, 20210119012048, 'TestUser8', '62751');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (9, 20210119012048, 'TestUser9', '27431');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (10, 20210119012048, 'TestUser10', 'CIT01');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (11, 20210119012048, 'TestUser11', 'F9S21');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (12, 20210119012048, 'TestUser12', 'X1381');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (13, 20210119012048, 'TestUser13', '3U071');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (14, 20210119012048, 'TestUser14', 'H3G11');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (15, 20210119012048, 'TestUser15', 'E1321');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (16, 20210119012048, 'TestUser16', 'JC631');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (17, 20210119012048, 'TestUser17', '8WJ01');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (18, 20210119012048, 'TestUser18', 'D6S01');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (19, 20210119012048, 'TestUser19', 'NI511');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (20, 20210119012048, 'TestUser20', 'E6F91');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (21, 20210119012048, 'TestUser21', 'HO201');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (22, 20210119012049, 'TestUser22', 'NFB61');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (23, 20210119012049, 'TestUser23', 'D8R51');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (24, 20210119012049, 'TestUser24', '85G71');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (25, 20210119012049, 'TestUser25', '8AT11');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (26, 20210119012049, 'TestUser26', '91231');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (27, 20210119012049, 'TestUser27', '57F21');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (28, 20210119012049, 'TestUser28', '25651');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (29, 20210119012049, 'TestUser29', '41O81');
INSERT INTO kakao.user (id, created_at, name, password) VALUES (30, 20210119012049, 'TestUser30', 'ZTV01');
UNLOCK TABLES;


LOCK TABLES `room_user` WRITE;
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('AAA17', 20210119012049, 'AAA', 17);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('AAA20', 20210119012049, 'AAA', 20);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('AAA3', 20210119012050, 'AAA', 3);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('AAA4', 20210119012049, 'AAA', 4);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('AAA5', 20210119012050, 'AAA', 5);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('BBB15', 20210119012050, 'BBB', 15);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('BBB29', 20210119012050, 'BBB', 29);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('BBB4', 20210119012050, 'BBB', 4);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('CCC10', 20210119012050, 'CCC', 10);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('CCC13', 20210119012050, 'CCC', 13);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('CCC18', 20210119012050, 'CCC', 18);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('CCC20', 20210119012050, 'CCC', 20);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('CCC28', 20210119012050, 'CCC', 28);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('DDD20', 20210119012050, 'DDD', 20);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('DDD27', 20210119012050, 'DDD', 27);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('DDD28', 20210119012050, 'DDD', 28);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('DDD4', 20210119012050, 'DDD', 4);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('DDD7', 20210119012050, 'DDD', 7);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('EEE10', 20210119012051, 'EEE', 10);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('EEE19', 20210119012050, 'EEE', 19);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('EEE2', 20210119012051, 'EEE', 2);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('EEE25', 20210119012050, 'EEE', 25);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('EEE3', 20210119012051, 'EEE', 3);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('FFF23', 20210119012051, 'FFF', 23);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('FFF27', 20210119012051, 'FFF', 27);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('FFF4', 20210119012051, 'FFF', 4);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('FFF6', 20210119012051, 'FFF', 6);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('FFF7', 20210119012051, 'FFF', 7);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('GGG17', 20210119012051, 'GGG', 17);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('GGG2', 20210119012051, 'GGG', 2);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('GGG28', 20210119012051, 'GGG', 28);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('GGG8', 20210119012051, 'GGG', 8);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('HHH17', 20210119012051, 'HHH', 17);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('HHH18', 20210119012051, 'HHH', 18);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('HHH27', 20210119012052, 'HHH', 27);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('HHH4', 20210119012051, 'HHH', 4);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('HHH5', 20210119012052, 'HHH', 5);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('III12', 20210119012052, 'III', 12);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('III16', 20210119012052, 'III', 16);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('III18', 20210119012052, 'III', 18);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('III4', 20210119012052, 'III', 4);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('JJJ10', 20210119012052, 'JJJ', 10);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('JJJ11', 20210119012052, 'JJJ', 11);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('JJJ12', 20210119012052, 'JJJ', 12);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('JJJ21', 20210119012052, 'JJJ', 21);
INSERT INTO kakao.room_user (id, join_at, room_id, user_id) VALUES ('JJJ25', 20210119012052, 'JJJ', 25);
UNLOCK TABLES;


LOCK TABLES `money_sprinkle` WRITE;
INSERT INTO kakao.money_sprinkle (seq, expire_at, receive_count, sprinkle_at, sprinkle_money, token, sprinkle_user_id) VALUES (1, 20210126014821, 3, 20210119014821, 50000, 'RU9', 'AAA3');
INSERT INTO kakao.money_sprinkle (seq, expire_at, receive_count, sprinkle_at, sprinkle_money, token, sprinkle_user_id) VALUES (2, 20210126014856, 2, 20210119014856, 20000, '5J1', 'BBB4');
UNLOCK TABLES;


LOCK TABLES `money_receive` WRITE;
INSERT INTO kakao.money_receive (seq, expire_at, receive_at, receive_money, receive_status, receive_user, money_sprinkle_seq) VALUES (1, 20210269015821, 0, 16666, 'N', null, 1);
INSERT INTO kakao.money_receive (seq, expire_at, receive_at, receive_money, receive_status, receive_user, money_sprinkle_seq) VALUES (2, 20210269015821, 0, 16666, 'N', null, 1);
INSERT INTO kakao.money_receive (seq, expire_at, receive_at, receive_money, receive_status, receive_user, money_sprinkle_seq) VALUES (3, 20210269015822, 0, 16668, 'N', null, 1);
INSERT INTO kakao.money_receive (seq, expire_at, receive_at, receive_money, receive_status, receive_user, money_sprinkle_seq) VALUES (4, 20210269015856, 0, 10000, 'N', null, 2);
INSERT INTO kakao.money_receive (seq, expire_at, receive_at, receive_money, receive_status, receive_user, money_sprinkle_seq) VALUES (5, 20210269015856, 0, 10000, 'N', null, 2);
UNLOCK TABLES;