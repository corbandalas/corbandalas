create table post_tags
(
    post_id uuid not null
        constraint fkscf0cxqrflq236keg9iwm19sr
            references post,
    tags     varchar(255)
);

alter table post_tags
    owner to postgres;

create index post_tags_post_id_idx
    on post_tags (post_id);


