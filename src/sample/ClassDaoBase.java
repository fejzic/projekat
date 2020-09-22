package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class ClassDaoBase {
    private static ClassDaoBase instance;
    private Connection conn;

    private PreparedStatement getStudentQuery,getLibraryQuery,getStaffQuery,getBookQuery,getBorrowsQuery,
    getPublisherQuery,getCategoryQuery,getUserAccountQuery,getAdminAccountQuery,;

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
