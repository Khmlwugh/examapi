CREATE TABLE choices (
    id BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL REFERENCES questions(id),
    choice_text TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL DEFAULT false,
    position SMALLINT NOT NULL
);