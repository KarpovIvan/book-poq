package com.book.poq.model;

import java.io.Serializable;

public class BookV0 implements Book, Serializable {
    private int id;
    private String title;
    private String author;
    private String genre;
    private Advert advert;
    // Publication info
    private String isbn;
    private String publisher;
    private String coverArtist;
    private String language;
    private short publicationDate;
    private String country;
    private short pages;

    @Override
    public String getVersion() {
        return "v0";
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
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

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCoverArtist() {
        return coverArtist;
    }

    public void setCoverArtist(String coverArtist) {
        this.coverArtist = coverArtist;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public short getPages() {
        return pages;
    }

    public void setPages(short pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", advert=" + ((advert != null)? advert.getText() : "null") +
                ", isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                ", coverArtist='" + coverArtist + '\'' +
                ", language='" + language + '\'' +
                ", publicationDate=" + publicationDate +
                ", country='" + country + '\'' +
                ", pages=" + pages +
                '}';
    }
}
