create table address_coordinates(
    id uuid primary key,
    cep varchar unique not null,
    latitude numeric not null,
    longitude numeric not null
);

CREATE INDEX idx_address_coordinates_cep ON address_coordinates (cep);

insert into address_coordinates (id, cep, latitude, longitude) values
('0f18456f-5328-4ef3-82fb-77a41711afce', '02040070', -23.4947731, -46.6163366),
('32fbcea6-c70b-4097-afa5-7952ef3845f5', '90550001', -30.0233056, -51.2031394),
('a626a870-88ad-43c9-bb41-64659c634eb8', '69050001', -3.092485, -60.0279166);
