package sample;

import java.util.Date;

public class Borrows {
    private int id;
    private int bookId;
    private String borrowedFrom;
    private Date borrowedTill;
    private Date actual_return;
    private Date issuedBy;
    private int studentId;

    public Borrows(int id, int bookId, String borrowedFrom, Date borrowedTill, Date actual_return, Date issuedBy, int studentId) {
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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBorrowedFrom() {
        return borrowedFrom;
    }

    public void setBorrowedFrom(String borrowedFrom) {
        this.borrowedFrom = borrowedFrom;
    }

    public Date getBorrowedTill() {
        return borrowedTill;
    }

    public void setBorrowedTill(Date borrowedTill) {
        this.borrowedTill = borrowedTill;
    }

    public Date getActual_return() {
        return actual_return;
    }

    public void setActual_return(Date actual_return) {
        this.actual_return = actual_return;
    }

    public Date getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(Date issuedBy) {
        this.issuedBy = issuedBy;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
