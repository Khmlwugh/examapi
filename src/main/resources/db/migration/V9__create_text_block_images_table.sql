-- V9__create_text_block_images_table.sql
CREATE TABLE text_block_images (
    id BIGSERIAL PRIMARY KEY,
    text_block_id BIGINT NOT NULL REFERENCES text_blocks(id),
    image_url VARCHAR(500) NOT NULL,
    position SMALLINT NOT NULL DEFAULT 1
);