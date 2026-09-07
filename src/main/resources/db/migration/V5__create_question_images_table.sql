CREATE TABLE question_images (
    id BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL REFERENCES questions(id),
    image_url VARCHAR(500) NOT NULL,
    position SMALLINT NOT NULL DEFAULT 1
);