package com.book.poq.model;

import java.io.Serializable;

public class BookV1 implements Book, Serializable {
    private int id;
    private String title;
    private String author;
    private String genre;
    private Advert advert;
    private String language;
    private short publicationDate;
    private short pages;

    private String annotation;
    private boolean withPictures;

    @Override
    public String getVersion() {
        return "v1";
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public short getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(short publicationDate) {
        this.publicationDate = publicationDate;
    }

    public short getPages() {
        return pages;
    }

    public void setPages(short pages) {
        this.pages = pages;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public boolean isWithPictures() {
        return withPictures;
    }

    public void setWithPictures(boolean withPictures) {
        this.withPictures = withPictures;
    }

    @Override
    public String toString() {
        return "BookV1{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", advert=" + ((advert != null)? advert.getText() : "null") +
                ", language='" + language + '\'' +
                ", publicationDate=" + publicationDate +
                ", pages=" + pages +
                ", annotation='" + annotation + '\'' +
                ", withPictures=" + withPictures +
                '}';
    }
}
