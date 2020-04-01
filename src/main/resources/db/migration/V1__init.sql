DROP TABLE IF EXISTS books;

CREATE TABLE books (
  id SERIAL PRIMARY KEY,
  title CHAR(250) NOT NULL,
  author CHAR(250) NOT NULL
);

INSERT INTO books (title, author) VALUES
  ('Neuromancer', 'William Ford Gibson'),
  ('1984', 'George Orwell'),
  ('The End of Eternity', 'Isaac Asimov');