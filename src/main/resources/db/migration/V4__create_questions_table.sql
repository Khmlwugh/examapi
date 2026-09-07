CREATE TABLE questions (
    id BIGSERIAL PRIMARY KEY,
    college_id BIGINT NOT NULL REFERENCES colleges(id),
    subject_id BIGINT NOT NULL REFERENCES subjects(id),
    text_block_id BIGINT REFERENCES text_blocks(id),
    exam_period VARCHAR(10) NOT NULL,
    question_text TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);