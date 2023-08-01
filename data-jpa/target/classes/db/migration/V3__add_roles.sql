CREATE TABLE customer_role
(
    id   UUID         NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE CUSTOMER_ROLES
(

    customer_id UUID REFERENCES customer (id),
    role_id     UUID REFERENCES customer_role (id)

);




