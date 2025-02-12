CREATE TABLE User (
  user_id VARCHAR(50) UNIQUE PRIMARY KEY NOT NULL,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  phone VARCHAR(20) UNIQUE NOT NULL,
  user_image VARCHAR(255) DEFAULT ''
);

CREATE TABLE Pet (
  pet_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id VARCHAR(50),
  pet_name VARCHAR(50) NOT NULL,
  pet_species VARCHAR(50),
  pet_age VARCHAR(10),
  pet_gender VARCHAR(10),
  pet_image VARCHAR(255) DEFAULT ''
);

CREATE TABLE Health_Record (
  record_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  pet_id INT,
  weight DECIMAL(5,2),
  height DECIMAL(5,2),
  medical_history TEXT,
  vaccination_status VARCHAR(100),
  checkup_status VARCHAR(100),
  date VARCHAR(50),
  hr_date TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE Diary (
  diary_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  diary_name VARCHAR(50) NOT NULL,
  pet_id INT,
  diary_date TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP),
  diary_content TEXT
);

CREATE TABLE Album (
  album_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  pet_id INT,
  album_image VARCHAR(255) NOT NULL,
  album_desc TEXT,
  album_tags VARCHAR(255),
  album_date TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE Comu_Post (
  post_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id VARCHAR(50),
  comu_title VARCHAR(255) NOT NULL,
  comu_content TEXT NOT NULL,
  comu_date TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP),
  comu_image VARCHAR(255)
);

CREATE TABLE Cmt (
  cmt_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  post_id INT,
  user_id VARCHAR(50),
  cmt_content TEXT NOT NULL,
  cmt_date TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE Msg (
  msg_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  msg_title VARCHAR(50) NOT NULL,
  sender_id VARCHAR(50),
  receiver_id VARCHAR(50),
  msg_content TEXT NOT NULL,
  msg_date timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE Vote (
  vote_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id VARCHAR(50),
  pet_id INT,
  vote_like INT DEFAULT 0,
  vote_image VARCHAR(255),
  vote_date timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE Vote_mgr (
  vote_id INT,
  vt_user_id VARCHAR(50)
);

ALTER TABLE `Pet` ADD FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE;

ALTER TABLE `Health_Record` ADD FOREIGN KEY (`pet_id`) REFERENCES `Pet` (`pet_id`) ON DELETE CASCADE;

ALTER TABLE `Diary` ADD FOREIGN KEY (`pet_id`) REFERENCES `Pet` (`pet_id`) ON DELETE CASCADE;

ALTER TABLE `Album` ADD FOREIGN KEY (`pet_id`) REFERENCES `Pet` (`pet_id`) ON DELETE CASCADE;

ALTER TABLE `Comu_Post` ADD FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE;

ALTER TABLE `Cmt` ADD FOREIGN KEY (`post_id`) REFERENCES `Comu_Post` (`post_id`) ON DELETE CASCADE;

ALTER TABLE `Cmt` ADD FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE;

ALTER TABLE `Msg` ADD FOREIGN KEY (`sender_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE;

ALTER TABLE `Msg` ADD FOREIGN KEY (`receiver_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE;

ALTER TABLE `Vote` ADD FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE;

ALTER TABLE `Vote` ADD FOREIGN KEY (`pet_id`) REFERENCES `Pet` (`pet_id`) ON DELETE CASCADE;

ALTER TABLE `Vote_mgr` ADD FOREIGN KEY (`vote_id`) REFERENCES `Vote` (`vote_id`) ON DELETE CASCADE;

ALTER TABLE `Vote_mgr` ADD FOREIGN KEY (`vt_user_id`) REFERENCES `User` (`user_id`);
