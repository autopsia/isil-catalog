create table City(
    cityId identity primary key,
    name varchar(100) not null,
    countryId integer null
);

create table Country(
    countryId identity primary key,
    name varchar(100) not null
);