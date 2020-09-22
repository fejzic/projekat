BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `library` (
     `id`	INTEGER,
     `building_name`	TEXT,
     `contact`	NUMBER,
     PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `staff`(
       `id`	INTEGER,
       `staff_name`	TEXT,
       PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `student`(
       `id`	INTEGER,
       `student_name`	TEXT,
       `borrow_id`	INTEGER,
       `department`	INTEGER,
       `contact`	NUMBER,
       PRIMARY KEY (`id`),
       FOREIGN KEY (`borrow_id`) REFERENCES `borrows (`id`)
);


CREATE TABLE IF NOT EXISTS `book`(
    `isbn`	INTEGER,
    `author`	TEXT,
    `title`	TEXT,
    `library_id`	INTEGER,
    `publisher_id`	INTEGER,
    `year`	DATE,
    `category_id`	TEXT,
    PRIMARY KEY (`isbn`),
    FOREIGN KEY (`library_id`) REFERENCES `library` (`id`),
    FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`),
    FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
);
CREATE TABLE IF NOT EXISTS `borrows`(
      `id`	INTEGER,
      `book_id`	INTEGER,
      `borrowed_from`	TEXT,
      `borrowed_till`	INTEGER,
      `actual_return`	INTEGER,
      `issued_by`	INTEGER,
      `student_id`	INTEGER,
      PRIMARY KEY (`id`),
      FOREIGN KEY (`book_id`) REFERENCES `book` (`isbn`),
      FOREIGN KEY (`issued_by`) REFERENCES `staff` (`id`),
      FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
);
CREATE TABLE IF NOT EXISTS `publisher`(
       `id`	INTEGER,
       `name`	TEXT,
        PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `category`(
       `id`	INTEGER,
       `name`	TEXT,
        PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `user_account`
(
    `id`       INTEGER,
    `username` text,
    `password` text,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `admin_account`
(
    `id`       INTEGER,
    `username` text,
    `password` text,
    PRIMARY KEY (`id`)
);
COMMIT;








