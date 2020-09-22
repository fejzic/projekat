package sample;

import java.time.Year;

public class Book {
    private int isbn;
    private String author;
    private String title;
    private int libraryId;
    private int publisherId;
    private Year year;
    private String categoryId;

    public Book(int isbn, String author, String title, int libraryId, int publisherId, Year year, String categoryId) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.libraryId = libraryId;
        this.publisherId = publisherId;
        this.year = year;
        this.categoryId = categoryId;
    }

    public Book() {
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
