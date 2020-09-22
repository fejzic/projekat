package sample;

public class Student {
    private int id;
    private String studentName;
    private int borrowId;
    private int department;
    private Number contact;

    public Student(int id, String studentName, int borrowId, int department, Number contact) {
        this.id = id;
        this.studentName = studentName;
        this.borrowId = borrowId;
        this.department = department;
        this.contact = contact;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public Number getContact() {
        return contact;
    }

    public void setContact(Number contact) {
        this.contact = contact;
    }
}

