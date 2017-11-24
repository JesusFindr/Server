CREATE TABLE `users` (
  `username` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users_profiles` (
  `username` varchar(30) NOT NULL DEFAULT '',
  `age` int(3) NOT NULL,
  `image` int(3) NOT NULL,
  `sex` int(3) NOT NULL,
  `shoe_size` int(11) DEFAULT NULL,
  `bellybutton` int(11) DEFAULT NULL,
  `spirit_animal` varchar(30) NOT NULL DEFAULT '',
  `brows_type` int(11) DEFAULT NULL,
  `back_hair` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `fk_foreignuser` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `matches` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user1` varchar(30) NOT NULL DEFAULT '',
  `user2` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;