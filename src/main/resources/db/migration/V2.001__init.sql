CREATE TABLE books_v2(
    id SERIAL PRIMARY KEY,
    snapshot jsonb NOT NULL
);