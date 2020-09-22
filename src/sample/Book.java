package sample;

import java.time.Year;

public class Book {
    private int isbn;
    private String author;
    private String title;
    Library libraryId;
    Publisher publisherId;
    Category categoryId;

    public Book(int isbn, String author, String title, Library libraryId, Publisher publisherId, Category categoryId) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.libraryId = libraryId;
        this.publisherId = publisherId;
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

    public Library getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Library libraryId) {
        this.libraryId = libraryId;
    }

    public Publisher getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Publisher publisherId) {
        this.publisherId = publisherId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }
}
