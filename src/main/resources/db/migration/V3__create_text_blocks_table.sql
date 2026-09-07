CREATE TABLE text_blocks (
    id BIGSERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    subject_id BIGINT REFERENCES subjects(id),
    created_at TIMESTAMP NOT NULL DEFAULT now()
);