DROP TABLE IF EXISTS phone;
DROP TABLE IF EXISTS company;

CREATE TABLE company
(
    companyID integer not null,
    companyName varchar not null,
    companyCountry varchar not null,
    PRIMARY KEY (companyID)
);

CREATE TABLE phone
(
    phoneId integer not null,
    phoheModel varchar not null,
    companyId integer not null,
    price decimal,
    PRIMARY KEY (phoneId),
    FOREIGN KEY (companyId) REFERENCES company(COMPANYID)

);

INSERT INTO company(COMPANYID, COMPANYNAME, COMPANYCOUNTRY)
VALUES
(1,'Samsung','Korea'),
(2,'Huawei','China'),
(3,'Nokia','Finland'),
(4,'Apple','USA');

INSERT INTO phone(phoneId, phoheModel, companyId, price) VALUES
(1,'Galaxy3',1,1500),
(2,'Galaxy4',1,2000),
(3,'Galaxy5',1,2500),
(4,'Honor6',2,150),
(5,'Honor8',2,250),
(6,'IPhone',4,555);
