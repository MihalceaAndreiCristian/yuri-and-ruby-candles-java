CREATE TABLE if NOT EXISTS users
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    username text NOT NULL,
    password text,
    email text,
    first_name text,
    last_name text,
    birth_date date,
    photo bytea,
    role text NOT NULL,
    "is_active" boolean,
    PRIMARY KEY (id),
    CONSTRAINT username_unique UNIQUE (username)
);

ALTER TABLE IF EXISTS users
    OWNER to flyway;