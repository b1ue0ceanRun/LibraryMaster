CREATE TABLE `books` (
  `book_id` varchar(45) NOT NULL,
  `book_name` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `condition` tinyint NOT NULL,
  PRIMARY KEY (`book_id`,`book_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `records` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `student_id` varchar(45) NOT NULL,
  `book_id` varchar(45) NOT NULL,
  `book_name` varchar(45) NOT NULL,
  `check` int NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `students` (
  `student_id` varchar(45) NOT NULL,
  `student_name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
SELECT * FROM sql_library.records;