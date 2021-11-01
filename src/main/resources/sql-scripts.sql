   CREATE TABLE customer (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `birth_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `document` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `phone` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=264 DEFAULT CHARSET=latin1;

CREATE TABLE address (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `city` varchar(255) NOT NULL,
    `complement` varchar(255) NOT NULL,
    `country` varchar(255) NOT NULL,
    `district` varchar(255) NOT NULL,
    `number` int(11) NOT NULL,
    `state` varchar(255) NOT NULL,
    `street` varchar(255) NOT NULL,
    `zip_code` varchar(255) NOT NULL,
    `customer_id` int(11) NOT NULL,
    PRIMARY KEY (`id`)
    KEY `fk_customer` (`customer_id`),
    CONSTRAINT `fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=264 DEFAULT CHARSET=latin1;


create table profiles (
    `profiles` int(11)
    `customer_id` int(11) NOT NULL,
    KEY `fk_customer` (`customer_id`),
    CONSTRAINT `fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=264 DEFAULT CHARSET=latin1;