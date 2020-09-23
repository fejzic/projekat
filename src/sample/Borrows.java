package sample;

import java.util.Date;

public class Borrows {
    private int id;
    private Book bookId;
    private String borrowedFrom;
    private int borrowedTill;
    private int actual_return;
    private Staff issuedBy;
    private Student studentId;

    public Borrows(int id, Book bookId, String borrowedFrom, int borrowedTill, int actual_return, Staff issuedBy, Student studentId) {
        this.id = id;
        this.bookId = bookId;
        this.borrowedFrom = borrowedFrom;
        this.borrowedTill = borrowedTill;
        this.actual_return = actual_return;
        this.issuedBy = issuedBy;
        this.studentId = studentId;
    }

    public Borrows() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getBorrowedFrom() {
        return borrowedFrom;
    }

    public void setBorrowedFrom(String borrowedFrom) {
        this.borrowedFrom = borrowedFrom;
    }

    public int getBorrowedTill() {
        return borrowedTill;
    }

    public void setBorrowedTill(int borrowedTill) {
        this.borrowedTill = borrowedTill;
    }

    public int getActual_return() {
        return actual_return;
    }

    public void setActual_return(int actual_return) {
        this.actual_return = actual_return;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public Staff getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(Staff issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }
}
