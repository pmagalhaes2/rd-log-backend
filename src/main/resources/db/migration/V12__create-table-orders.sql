CREATE table orders (
    id serial primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    supplier_id int not null,
    origin_address_id int not null,
    destination_address_id int not null,
    status varchar(255) not null,
    foreign key (origin_address_id) references address(id),
    foreign key (destination_address_id) references address(id)
);

