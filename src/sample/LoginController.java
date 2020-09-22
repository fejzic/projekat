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

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LoginController {
    public PasswordField fldPassword;
    public TextField fldUserName;
    private static ClassDaoBase baza;


    public void initialize() {
        this.baza = ClassDaoBase.getInstance();
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
