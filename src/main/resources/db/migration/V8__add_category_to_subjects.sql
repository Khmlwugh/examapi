ALTER TABLE subjects
ADD COLUMN category_id BIGINT REFERENCES categories(id);