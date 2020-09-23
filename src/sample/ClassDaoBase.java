package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClassDaoBase {
    private static ClassDaoBase instance;
    private Connection conn;

    private PreparedStatement getStudentQuery, getLibraryQuery, getStaffQuery, getBookQuery, getBorrowsQuery,
            getPublisherQuery, getCategoryQuery, getUserAccountQuery, getAdminAccountQuery, deleteStudentQuery, deleteLibraryQuery, deleteStaffQuery,
            deleteBookQuery, deleteBorrowsQuery, deletePublisherQuery, deleteCategoryQuery, deleteUserAccountQuery, deleteAdminAccountQuery,
            addStudentQuery, addLibraryQuery, addStaffQuery, addBookQuery, addBorrowsQuery, addPublisherQuery, addCategoryQuery,
            addUserAccountQuery, addAdminAccountQuery, findBookQuery, findCategoryQuery, findStudentQuery,
            addUserNameQuery, addPasswordQuery;

    public static ClassDaoBase getInstance() {
        if (instance == null) instance = new ClassDaoBase();
        return instance;
    }

    private ClassDaoBase() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            addUserNameQuery = conn.prepareStatement("SELECT * FROM user_account WHERE user_name=?");
        } catch (SQLException e) {
            regenerateBase();
            try {
                addUserNameQuery = conn.prepareStatement("SELECT * FROM user_account WHERE user_name=?");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        try {
            getStudentQuery = conn.prepareStatement("SELECT * FROM student WHERE id=? ");
            getBookQuery = conn.prepareStatement("SELECT * FROM book WHERE isbn = ?");
            getBorrowsQuery = conn.prepareStatement("SELECT * FROM borrows WHERE id=?");
            getCategoryQuery = conn.prepareStatement("SELECT * FROM category WHERE id=?");
            getLibraryQuery = conn.prepareStatement("SELECT * FROM library WHERE id=?");
            getPublisherQuery = conn.prepareStatement("SELECT * FROM publisher WHERE id=?");
            getStaffQuery = conn.prepareStatement("SELECT * FROM staff WHERE id=?");
            getAdminAccountQuery = conn.prepareStatement("SELECT * FROM admin_account WHERE id=?");
            getUserAccountQuery = conn.prepareStatement("SELECT * FROM user_account WHERE first_name=?");
            deleteAdminAccountQuery = conn.prepareStatement("DELETE FROM admin_account WHERE id=?");
            deleteUserAccountQuery = conn.prepareStatement("DELETE FROM user_account WHERE first_name=?");
            deleteBookQuery = conn.prepareStatement("DELETE FROM book WHERE isbn=?");
            deleteBorrowsQuery = conn.prepareStatement("DELETE FROM borrows WHERE id=?");
            deleteCategoryQuery = conn.prepareStatement("DELETE FROM category WHERE id=?");
            deleteLibraryQuery = conn.prepareStatement("DELETE FROM library WHERE id=?");
            deletePublisherQuery = conn.prepareStatement("DELETE FROM publisher WHERE id=?");
            deleteStaffQuery = conn.prepareStatement("DELETE FROM staff WHERE id=?");
            deleteStudentQuery = conn.prepareStatement("DELETE FROM student WHERE id=?");
            findBookQuery = conn.prepareStatement("SELECT * FROM book WHERE title=?");
            findCategoryQuery = conn.prepareStatement("SELECT * FROM category WHERE name=?");
            findStudentQuery = conn.prepareStatement("SELECT * FROM student WHERE student_name=?");
            addPasswordQuery = conn.prepareStatement("SELECT * FROM user_account WHERE user_name=?");


            addAdminAccountQuery = conn.prepareStatement("INSERT INTO admin_account VALUES(?,?,?)");
            addUserAccountQuery = conn.prepareStatement("INSERT INTO user_account VALUES(?,?,?,?)");
            addCategoryQuery = conn.prepareStatement("INSERT INTO category VALUES(?,?)");
            addPublisherQuery = conn.prepareStatement("INSERT INTO publisher VALUES(?,?)");
            addBorrowsQuery = conn.prepareStatement("INSERT INTO borrows VALUES(?,?,?,?,?,?,?)");
            addBookQuery = conn.prepareStatement("INSERT INTO book VALUES(?,?,?,?,?,?)");
            addStudentQuery = conn.prepareStatement("INSERT INTO student VALUES(?,?,?,?,?)");
            addStaffQuery = conn.prepareStatement("INSERT INTO staff VALUES(?,?)");
            addLibraryQuery = conn.prepareStatement("INSERT INTO library  VALUES(?,?,?)");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeInstance() {
        if (instance == null) return;
        instance.close();
        instance = null;
    }

    private void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void regenerateBase() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("baza.db.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if (sqlUpit.charAt(sqlUpit.length() - 1) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void registerUserName(String firstName, String lastName, String userName, String password) {
        try {
            addUserAccountQuery.setString(1, firstName);
            addUserAccountQuery.setString(2, lastName);
            addUserAccountQuery.setString(3, userName);
            addUserAccountQuery.setString(4, password);
            addUserAccountQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validateUserName(String userName) throws SQLException {
        addUserNameQuery.setString(1, userName);
        ResultSet rs = addUserNameQuery.executeQuery();
        if (!rs.next()) return false;
        return true;
    }

    public String validatePassword(String userName) throws SQLException {
        addPasswordQuery.setString(1, userName);
        ResultSet rs = addPasswordQuery.executeQuery();
        if (!rs.next()) return null;
        return rs.getString(4);
    }

    private Library getLibraryFromRs(ResultSet rs) throws SQLException {
        Library library = new Library(rs.getInt(1), rs.getString(2), rs.getInt(3));
        return library;

    }

    private Student getStudentFromRs(ResultSet rs, Borrows b) throws SQLException {
        Student student = new Student(rs.getInt(1), rs.getString(2), b, rs.getInt(4), rs.getInt(5));
        return student;
    }

    private Book getBookFromRs(ResultSet rs) throws SQLException {
        Library l = null;
        Publisher p = null;
        Category c = null;
        Book b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), l, p, c);
        return b;
    }

    private UserAccount getUserAccoutFromRs(ResultSet rs) throws SQLException {
        UserAccount us = new UserAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
        return us;
    }

    private ArrayList<Book> books() {
        ArrayList<Book> rez = new ArrayList<>();
        try {
            ResultSet rs = getBookQuery.executeQuery();
            while (rs.next()) {
                Book book = getBookFromRs(rs);
                rez.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rez;
    }

    public void addBook(Book book) {
        try {
            addBookQuery.setInt(1, book.getIsbn());
            addBookQuery.setString(2, book.getAuthor());
            addBookQuery.setString(3, book.getTitle());
            addBookQuery.setInt(4, book.getLibraryId().getId());
            addBookQuery.setInt(5, book.getPublisherId().getId());
            addBookQuery.setInt(6, book.getCategoryId().getId());
            addBookQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        try {
            addStudentQuery.setInt(1, student.getId());
            addStudentQuery.setString(2, student.getStudentName());
            addStudentQuery.setInt(3, student.getBorrowId().getId());
            addStudentQuery.setInt(4, student.getDepartment());
            addStudentQuery.setInt(5, (Integer) student.getContact());
            addStudentQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUserAccount(UserAccount userAccount) {
        try {
            addUserAccountQuery.setString(1, userAccount.getFirstName());
            addUserAccountQuery.setString(2, userAccount.getLastName());
            addUserAccountQuery.setString(3, userAccount.getUserName());
            addUserAccountQuery.setString(4, userAccount.getPassword());
            addUserAccountQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCategory(Category category){
        try {
            addCategoryQuery.setInt(1,category.getId());
            addCategoryQuery.setString(2,category.getName());
            addCategoryQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBorrow(Borrows borrows){
        try {
            addBorrowsQuery.setInt(1,borrows.getId());
            addBorrowsQuery.setInt(2,borrows.getBookId().getIsbn());
            addBorrowsQuery.setString(3,borrows.getBorrowedFrom());
            addBorrowsQuery.setInt(4, borrows.getBorrowedTill());
            addBorrowsQuery.setInt(5,borrows.getActual_return());
            addBorrowsQuery.setInt(6,borrows.getIssuedBy().getId());
            addBorrowsQuery.setInt(7,borrows.getStudentId().getId());
            addBorrowsQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
















}









