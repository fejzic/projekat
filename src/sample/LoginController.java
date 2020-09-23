package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LoginController {
    public PasswordField fldPassword;
    public TextField fldUserName;
    private static ClassDaoBase baza;
    private UserAccount userAccount;


    public void initialize() {
        this.baza = ClassDaoBase.getInstance();

        fldUserName.textProperty().addListener((obs, oldIme, newIme) -> {

            String pattern = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(newIme);
            if (!newIme.isEmpty() && newIme.length() <= 16 && m.find()) {
                fldUserName.getStyleClass().removeAll("poljeNijeIspravno");
                fldUserName.getStyleClass().add("poljeIspravno");
            } else {
                fldUserName.getStyleClass().removeAll("poljeIspravno");
                fldUserName.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldPassword.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty() ){

                fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
                fldPassword.getStyleClass().add("poljeIspravno");

            } else {
                fldPassword.getStyleClass().removeAll("poljeIspravno");
                fldPassword.getStyleClass().add("poljeNijeIspravno");


            }

        });
    }

    public void actionSignUp(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/sing.fxml"));
            SignUpController ctrl = new SignUpController();
            loader.setController(ctrl);
            stage.setTitle("SignUp");

        try {
            root = loader.load();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean checkPassword(){
        String pass = userAccount.getPassword() ;
        String pattern = "(?=.*[A-Z])(?=.*[a-z])(?=.*?[0-9])(?=.*[@#$%^&+=*;.<>()ß¤×÷¸¸¨˘°°!:˛`˙˘ˇ~/])";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(pass);

        return m.find();
    }

    public void actLogin(ActionEvent actionEvent) throws SQLException {
        String password = fldPassword.getText();


       /* if(baza.validatePassword(fldUserName.getText()).equals(password)){
            System.out.println("ASD");
        }
        else {
            System.out.println("qwer");
        }*/
    }
}
