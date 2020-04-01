package com.book.poq.model;

public interface Book {

    int getId();

    String getVersion();

    Advert getAdvert();

    void setAdvert(Advert advert);

    String getAuthor();

    void setAuthor(String author);

    void setTitle(String title);

    String getTitle();

}
