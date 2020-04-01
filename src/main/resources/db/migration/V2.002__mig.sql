INSERT INTO books_v2(snapshot)
SELECT ('{"id":' || id ||',"title":"' || title || '","author":"' || author || '"}')::jsonb AS snapshot
FROM books;
DROP TABLE books;
