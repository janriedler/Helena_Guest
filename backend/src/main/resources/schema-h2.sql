CREATE TABLE IF NOT EXISTS guests(id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(30),   email VARCHAR(30), checkIn DATE, checkOut DATE, sonstiges VARCHAR,
                    raum VARCHAR(30), preis VARCHAR(30), gezahlt VARCHAR(30));