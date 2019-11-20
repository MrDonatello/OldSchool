DROP DATABASE IF EXISTS ttschool;
CREATE DATABASE `ttschool`; 
USE `ttschool`;

CREATE TABLE school (
id int (11) not null auto_increment,
 name varchar(50) not null, 
 year int, 
 primary key (id),
 UNIQUE KEY school (name, year),
 KEY name(name),
 KEY year(year)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8;
 
CREATE TABLE groups (
id  int (11) not null auto_increment,
 name varchar(50) not null,
 room varchar(50),
 schoolId int(11) not null,
 primary key (id),
 KEY name(name),
 KEY room(room),
 KEY schoolId(schoolId),
 FOREIGN KEY (schoolId) REFERENCES school (id) ON DELETE CASCADE
 ) ENGINE=INNODB DEFAULT CHARSET=utf8;
 
CREATE TABLE trainee (
 id  int (11) not null auto_increment,
 firstname varchar(50) not null,
 lastname varchar(50) not null ,
 rating int (11),
 groupId int (11), 
 primary key  (id),
 KEY groupId(groupId)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8;
 
CREATE TABLE subject (
id int (11) not null auto_increment,
 name varchar(50) not null,
 primary key (id),
  KEY name(name)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8;
 
 CREATE TABLE group_subject (
 id int not null auto_increment,
 groupId  int NOT NULL,
 subjectId int NOT NULL, 
 primary key (id),
 UNIQUE KEY group_subject (groupId,subjectId),
 KEY groupId (groupId),
 KEY subjectId (subjectId),
 FOREIGN KEY (groupId) REFERENCES groups (id) ON DELETE CASCADE,
 FOREIGN KEY (subjectId) REFERENCES subject (id) ON DELETE CASCADE
 ) ENGINE=INNODB DEFAULT CHARSET=utf8;

