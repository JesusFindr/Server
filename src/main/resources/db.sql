CREATE TABLE `users_login` (
  `username` VARCHAR(30) NOT NULL DEFAULT '',
  `password` VARCHAR(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`username`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE `users_details` (
  `username`      VARCHAR(30) NOT NULL DEFAULT '',
  `name`          VARCHAR(80)          DEFAULT NULL,
  `profile_image` INT(3)      NOT NULL,
  `age`           INT(11)              DEFAULT NULL,
  `sex`           INT(3)               DEFAULT NULL,
  PRIMARY KEY (`username`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `users_criteria` (
  `username`         VARCHAR(30) NOT NULL DEFAULT '',
  `shoe_size`        INT(11)              DEFAULT NULL,
  `bellybutton`      INT(11)              DEFAULT NULL,
  `spirit_animal`    INT(11)              DEFAULT NULL,
  `brows_type`       INT(11)              DEFAULT NULL,
  `fingers_in_hands` INT(11)              DEFAULT NULL,
  PRIMARY KEY (`username`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `matches` (
  `id`      INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `matched` BIT(1)           NOT NULL,
  `user1`   VARCHAR(30)      NOT NULL DEFAULT '',
  `user2`   VARCHAR(30)      NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;