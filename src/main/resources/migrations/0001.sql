--liquibase formatted sql

--changeset jwirth:1
CREATE TABLE IF NOT EXISTS friends (id SERIAL , user_id_a BIGINT UNSIGNED NOT NULL , user_id_b BIGINT UNSIGNED NOT NULL , PRIMARY KEY (`id`) )
--rollback drop table friends;