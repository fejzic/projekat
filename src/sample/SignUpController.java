package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.stage.StageStyle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController {
    public TextField fldUserNm;
    public TextField fldName;
    public TextField fldSurname;
    public PasswordField fldPass;
    private boolean dobarName;
    private UserAccount user;
    private boolean NameOk = false;
    private boolean SurnameOk = false;
    private boolean UsernameOk = false;
    private boolean PasswordOk = false;
    private boolean allOk = true;
    private ClassDaoBase baza;

    UserAccount userAccount;

    public SignUpController() {
baza = ClassDaoBase.getInstance();

    }

    public void initialize(){
        fldName.textProperty().addListener((obs, oldIme, newIme) -> {
            String pattern = "^[a-zA-Z\\s\\-]*$";
            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(newIme);


            if (!newIme.isEmpty() && newIme.length() >= 3 && m.find()) {
                fldName.getStyleClass().removeAll("poljeNijeIspravno");
                fldName.getStyleClass().add("poljeIspravno");
                NameOk = true;
            } else {
                fldName.getStyleClass().removeAll("poljeIspravno");
                fldName.getStyleClass().add("poljeNijeIspravno");
                NameOk = false;
                allOk = false;
            }
        });

        fldSurname.textProperty().addListener((obs, oldIme, newIme) -> {

            String pattern = "^[a-zA-Z\\s\\-]*$";
            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(newIme);
            if (!newIme.isEmpty() && newIme.length() >= 3 && m.find()) {
                fldSurname.getStyleClass().removeAll("poljeNijeIspravno");
                fldSurname.getStyleClass().add("poljeIspravno");
                SurnameOk = true;
            } else {
                fldSurname.getStyleClass().removeAll("poljeIspravno");
                fldSurname.getStyleClass().add("poljeNijeIspravno");
                SurnameOk = false;
                allOk = false;
            }
        });


        fldUserNm.textProperty().addListener((obs, oldIme, newIme) -> {

            String pattern = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(newIme);
            if (!newIme.isEmpty() && newIme.length() <= 16 && m.find()) {
                fldUserNm.getStyleClass().removeAll("poljeNijeIspravno");
                fldUserNm.getStyleClass().add("poljeIspravno");
                UsernameOk = true;
            } else {
                fldUserNm.getStyleClass().removeAll("poljeIspravno");
                fldUserNm.getStyleClass().add("poljeNijeIspravno");
                UsernameOk = false;
                allOk = false;
            }
        });

        fldPass.textProperty().addListener((obs, oldIme, newIme) -> {
            AdminAccount userAccount = new AdminAccount("","","",newIme);
            if (!newIme.isEmpty() && userAccount.checkPassword()){

                fldPass.getStyleClass().removeAll("poljeNijeIspravno");
                fldPass.getStyleClass().add("poljeIspravno");
                PasswordOk = true;

            } else {
                fldPass.getStyleClass().removeAll("poljeIspravno");
                fldPass.getStyleClass().add("poljeNijeIspravno");
                PasswordOk = false;
                allOk = false;


            }

        });
    }


    public void actSignUp(ActionEvent actionEvent) {


        if(NameOk && PasswordOk && UsernameOk && SurnameOk ) {
            baza.registerUserName(fldName.getText(),fldSurname.getText(),fldUserNm.getText(),fldPass.getText());
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully registered!");
            alert.showAndWait();

        }

    }

    public void actCancel(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }


}




