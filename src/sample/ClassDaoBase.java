package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class ClassDaoBase {
    private static ClassDaoBase instance;
    private Connection conn;

    private PreparedStatement getStudentQuery,getLibraryQuery,getStaffQuery,getBookQuery,getBorrowsQuery,
    getPublisherQuery,getCategoryQuery,getUserAccountQuery,getAdminAccountQuery,deleteStudentQuery,deleteLibraryQuery,deleteStaffQuery,
            deleteBookQuery,deleteBorrowsQuery,deletePublisherQuery,deleteCategoryQuery,deleteUserAccountQuery,deleteAdminAccountQuery,
            addStudentQuery,addLibraryQuery,addStaffQuery, addBookQuery,addBorrowsQuery,addPublisherQuery,addCategoryQuery,
            addUserAccountQuery,addAdminAccountQuery;

    public static ClassDaoBase getInstance(){
        if(instance == null) instance = new ClassDaoBase();
        return instance;
    }

    private ClassDaoBase(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            getStudentQuery = conn.prepareStatement("SELECT * FROM student WHERE id=? ");
            getBookQuery = conn.prepareStatement("SELECT * FROM book WHERE isbn = ?");
            getBorrowsQuery = conn.prepareStatement("SELECT * FROM borrows WHERE id=?");
            getCategoryQuery= conn.prepareStatement("SELECT * FROM category WHERE id=?");
            getLibraryQuery = conn.prepareStatement("SELECT * FROM library WHERE id=?");
            getPublisherQuery = conn.prepareStatement("SELECT * FROM publisher WHERE id=?");
            getStaffQuery = conn.prepareStatement("SELECT * FROM staff WHERE id=?");
            getAdminAccountQuery = conn.prepareStatement("SELECT * FROM admin_account WHERE id=?");
            getUserAccountQuery = conn.prepareStatement("SELECT * FROM user_account WHERE id=?");
            deleteAdminAccountQuery = conn.prepareStatement("DELETE FROM admin_account WHERE id=?");
            deleteUserAccountQuery = conn.prepareStatement("DELETE FROM user_account WHERE id=?");
            deleteBookQuery = conn.prepareStatement("DELETE FROM book WHERE isbn=?");
            deleteBorrowsQuery = conn.prepareStatement("DELETE FROM borrows WHERE id=?");
            deleteCategoryQuery = conn.prepareStatement("DELETE FROM category WHERE id=?");
            deleteLibraryQuery= conn.prepareStatement("DELETE FROM library WHERE id=?");
            deletePublisherQuery =conn.prepareStatement("DELETE FROM publisher WHERE id=?");
            deleteStaffQuery = conn.prepareStatement("DELETE FROM staff WHERE id=?");
            deleteStudentQuery = conn.prepareStatement("DELETE FROM student WHERE id=?");

            addAdminAccountQuery = conn.prepareStatement("INSERT INTO admin_account VALUES(?,?,?)");
            addUserAccountQuery = conn.prepareStatement("INSERT INTO user_account VALUES(?,?,?)");
            addCategoryQuery = conn.prepareStatement("INSERT INTO category VALUES(?,?)");
            addPublisherQuery = conn.prepareStatement("INSERT INTO publisher VALUES(?,?)");
            addBorrowsQuery = conn.prepareStatement("INSERT INTO borrows VALUES(?,?,?,?,?,?,?)");
            addBookQuery = conn.prepareStatement("INSERT INTO book VALUES(?,?,?,?,?,?,?)");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeInstance(){
        if(instance == null) return;
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

    private void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("baza.db.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if ( sqlUpit.charAt( sqlUpit.length()-1 ) == ';') {
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

}
