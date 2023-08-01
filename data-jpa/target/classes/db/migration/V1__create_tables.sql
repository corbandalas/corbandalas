CREATE TABLE customer
(
    id         UUID         NOT NULL,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL ,
    active     BOOLEAN default true,
    password   VARCHAR(255) NOT NULL,
    create_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE UNIQUE INDEX idx_customer_email_unq ON customer (email);


CREATE TABLE post
(
    id              UUID           NOT NULL,
    title           VARCHAR(255)   NOT NULL,
    text            VARCHAR(10000) NOT NULL,
    customer_id         UUID           NOT NULL,
    date TIMESTAMP WITHOUT TIME ZONE,
    deleted         BOOLEAN,
    CONSTRAINT pk_post PRIMARY KEY (id),
    CONSTRAINT FK_POST_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE comment
(
    id      UUID          NOT NULL,
    text    VARCHAR(1000) NOT NULL,
    post_id UUID,
    customer_id UUID,
    create_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_comment PRIMARY KEY (id),
    CONSTRAINT FK_COMMENT_ON_POST FOREIGN KEY (post_id) REFERENCES post (id),
    CONSTRAINT FK_COMMENT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id)
);
