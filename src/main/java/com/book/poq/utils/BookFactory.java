package com.book.poq.utils;

import com.book.poq.model.Book;
import com.book.poq.model.BookV0;
import com.book.poq.model.BookV1;

public class BookFactory {

    public static <T extends Book> Class<T> getBookClazzByVersion(String version){
        if (version.equals("v0"))
            return (Class<T>) BookV0.class;
        else if (version.equals("v1"))
            return (Class<T>) BookV1.class;
        else
            return null;
    }
    
    public static BookV1 getFilledBookV1(){
        BookV1 bV1 = new BookV1();
        bV1.setAuthor("Author");
        bV1.setGenre("Genre");
        bV1.setTitle("Title");
        bV1.setLanguage("Lang");
        bV1.setPublicationDate((short) 2000);
        bV1.setAnnotation("Annotation");
        bV1.setPages((short) 23);
        bV1.setWithPictures(true);
        return bV1;
    }

    public static BookV0 getFilledBookV0(){
        BookV0 bV0 = new BookV0();
        bV0.setAuthor("Author");
        bV0.setGenre("Genre");
        bV0.setTitle("Title");
        bV0.setLanguage("Lang");
        bV0.setPublicationDate((short) 2000);
        bV0.setPages((short) 23);
        bV0.setCoverArtist("Cover");
        bV0.setIsbn("23423=-2342-3423-4");
        bV0.setPublisher("Publisher");
        return bV0;
    }
}
