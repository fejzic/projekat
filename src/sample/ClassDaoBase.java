package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
            addUserNameQuery, addPasswordQuery,editBookQuery,findLibraryQuery,findBorrowQuery,findPublisherQuery,findStaffQuery,getAllCategoriesQuery,getAllPublishersQuery,getAllLibrariesQuery;

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
            getBookQuery = conn.prepareStatement("SELECT * FROM book");
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
            findCategoryQuery = conn.prepareStatement("SELECT * FROM category WHERE id=?");
            findStudentQuery = conn.prepareStatement("SELECT * FROM student WHERE student_name=?");
            addPasswordQuery = conn.prepareStatement("SELECT * FROM user_account WHERE user_name=?");
            findLibraryQuery = conn.prepareStatement("SELECT * from library where id=?");
            findBorrowQuery = conn.prepareStatement("SELECT * from borrows where id=?");
            findStaffQuery = conn.prepareStatement("SELECT * from staff where id=?");
            findPublisherQuery = conn.prepareStatement("SELECT * from publisher where id=?");
            getAllCategoriesQuery = conn.prepareStatement("SELECT * from category");
            getAllLibrariesQuery = conn.prepareStatement("SELECT * from library");
            getAllPublishersQuery = conn.prepareStatement("SELECT * from publisher");


            addAdminAccountQuery = conn.prepareStatement("INSERT INTO admin_account VALUES(?,?,?)");
            addUserAccountQuery = conn.prepareStatement("INSERT INTO user_account VALUES(?,?,?,?)");
            addCategoryQuery = conn.prepareStatement("INSERT INTO category VALUES(?,?)");
            addPublisherQuery = conn.prepareStatement("INSERT INTO publisher VALUES(?,?)");
            addBorrowsQuery = conn.prepareStatement("INSERT INTO borrows VALUES(?,?,?,?,?,?,?)");
            addBookQuery = conn.prepareStatement("INSERT INTO book VALUES(?,?,?,?,?,?)");
            addStudentQuery = conn.prepareStatement("INSERT INTO student VALUES(?,?,?,?)");
            addStaffQuery = conn.prepareStatement("INSERT INTO staff VALUES(?,?)");
            addLibraryQuery = conn.prepareStatement("INSERT INTO library  VALUES(?,?,?)");

            editBookQuery = conn.prepareStatement("UPDATE book SET author=?, title=?, library_id=?, publisher_id=?,category_id=? WHERE isbn=? ");

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

    public ObservableList<Library> libraries() throws SQLException {
        ObservableList<Library> libraries = FXCollections.observableArrayList();

        ResultSet rs = getAllLibrariesQuery.executeQuery();

        while(rs.next()){
            libraries.add(new Library(rs.getInt(1),rs.getString(2),rs.getInt(3)));
        }
        return libraries;
    }

    public ObservableList<Category> categories() throws SQLException {
        ObservableList<Category> categories = FXCollections.observableArrayList();

        ResultSet rs = getAllCategoriesQuery.executeQuery();

        while(rs.next()){
            categories.add(new Category(rs.getInt(1),rs.getString(2)));
        }
        return categories;
    }

    public ObservableList<Publisher> publishers() throws SQLException {
        ObservableList<Publisher> publishers = FXCollections.observableArrayList();

        ResultSet rs = getAllPublishersQuery.executeQuery();

        while(rs.next()){
            publishers.add(new Publisher(rs.getInt(1),rs.getString(2)));
        }
        return publishers;
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

    private Category findKategorija(int id) throws SQLException {
        findBookQuery.setInt(1,id);
        ResultSet rs = findBookQuery.executeQuery();
        Category category = new Category();

        if(rs.next()){
            category = new Category(rs.getInt(1),rs.getString(2));
        }
        return category;
    }

    private Library findLibrary(int id) throws SQLException {
        findLibraryQuery.setInt(1,id);
        ResultSet rs = findLibraryQuery.executeQuery();
        Library library = new Library();

        if(rs.next()){
            library = new Library(rs.getInt(1),rs.getString(2),rs.getInt(3));
        }
        return library;
    }

    private Staff findStaff(int id) throws SQLException {
        getStaffQuery.setInt(1,id);
        ResultSet rs = getStaffQuery.executeQuery();
        Staff staf = new Staff();
        if(rs.next()){
            staf = new Staff(rs.getInt(1),rs.getString(2));
        }
        return staf;
    }


    private Student findStudent(int id) throws SQLException {
        getStudentQuery.setInt(1,id);
        ResultSet rs = getStudentQuery.executeQuery();
        Student student = new Student();
        if(rs.next()){
            student = new Student(rs.getInt(1),rs.getString(2),findBorrow(rs.getInt(3)),rs.getInt(4),rs.getInt(5));
        }
        return student;
    }

    private Book getBook(int id) throws SQLException {
        findBookQuery.setInt(1,id);
        ResultSet rs = findBookQuery.executeQuery();
        Book book = new Book();
        if(rs.next()){
            book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), findLibrary(rs.getInt(4)), findPublisher(rs.getInt(5)), findKategorija(rs.getInt(6)));
        }

    return book;
    }
    private Borrows findBorrow(int id) throws SQLException {
        findBorrowQuery.setInt(1,id);
        ResultSet rs = findBorrowQuery.executeQuery();
        Borrows borrow = new Borrows();

        if(rs.next()){
            borrow = new Borrows(rs.getInt(1),getBook(rs.getInt(2)),rs.getString(3),rs.getInt(4),rs.getInt(5),findStaff(rs.getInt(6)),findStudent(rs.getInt(7)));
        }
        return borrow;
    }

    private Publisher findPublisher(int id) throws SQLException {
        Publisher publisher = new Publisher();
        findPublisherQuery.setInt(1,id);
        ResultSet rs = findPublisherQuery.executeQuery();

        if(rs.next()){
            publisher = new Publisher(rs.getInt(1),rs.getString(2));
        }
        return publisher;
    }

    private Book getBookFromRs(ResultSet rs) throws SQLException {
        Book b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), findLibrary(rs.getInt(4)), findPublisher(rs.getInt(5)), findKategorija(rs.getInt(6)));
        return b;
    }

    private UserAccount getUserAccoutFromRs(ResultSet rs) throws SQLException {
        UserAccount us = new UserAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
        return us;
    }

    public ObservableList<Book> books() {
        ObservableList<Book> rez = FXCollections.observableArrayList();
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









