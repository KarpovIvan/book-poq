DROP PROCEDURE save_new_book_with_version(book jsonb, vers char(50));
CREATE OR REPLACE FUNCTION save_book_with_version(book jsonb, vers char(50))
    RETURNS setof books_v2
AS
$$
DECLARE
    new_id int;
    returning_id int;
BEGIN
    IF (book->>'id')::int IN (SELECT id FROM books_v2) THEN
        UPDATE books_v2 SET snapshot = book WHERE id = (book->>'id')::int;
        returning_id = (book->>'id')::int;
    ELSE
        INSERT INTO books_v2(snapshot, version)
        VALUES (book, vers)
        RETURNING id INTO new_id;

        UPDATE books_v2
        SET snapshot = jsonb_set(snapshot, '{"id"}', new_id::char(250)::jsonb, false)
        WHERE id = new_id;
        returning_id = new_id;
    END IF;

    RETURN QUERY (SELECT * FROM books_v2 WHERE id = returning_id);
END
$$
LANGUAGE plpgsql;