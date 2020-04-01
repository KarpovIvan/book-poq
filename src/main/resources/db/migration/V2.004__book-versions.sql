ALTER TABLE books_v2 ADD COLUMN version char(50);
UPDATE books_v2 SET version = 'v0';

DROP PROCEDURE save_new_book(book jsonb);

CREATE OR REPLACE PROCEDURE save_new_book_with_version(book jsonb, vers char(50))
    LANGUAGE plpgsql AS
$$
DECLARE
    new_id int;
BEGIN
    IF (book->>'id')::int IN (SELECT id FROM books_v2) THEN
        UPDATE books_v2 SET snapshot = book WHERE id = (book->>'id')::int;
    ELSE
        INSERT INTO books_v2(snapshot, version)
        VALUES (book, vers)
        RETURNING id INTO new_id;

        UPDATE books_v2
        SET snapshot = jsonb_set(snapshot, '{"id"}', new_id::char(250)::jsonb, false)
        WHERE id = new_id;
    END IF;
END
$$;

