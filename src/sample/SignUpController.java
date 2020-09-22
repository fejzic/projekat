package sample;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController {
    public TextField fldUserName;
    public TextField fldName;
    public TextField fldSurname;
    public PasswordField fldPass;

    UserAccount userAccount;

    public SignUpController() {

    }

    public void initialize(){
        fldName.textProperty().addListener((obs, oldIme, newIme) -> {
            String pattern = "^[a-zA-Z\\s\\-]*$";
            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(newIme);


            if (!newIme.isEmpty() && newIme.length() >= 3 && m.find()) {
                fldName.getStyleClass().removeAll("poljeNijeIspravno");
                fldName.getStyleClass().add("poljeIspravno");
            } else {
                fldName.getStyleClass().removeAll("poljeIspravno");
                fldName.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldSurname.textProperty().addListener((obs, oldIme, newIme) -> {

            String pattern = "^[a-zA-Z\\s\\-]*$";
            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(newIme);
            if (!newIme.isEmpty() && newIme.length() >= 3 && m.find()) {
                fldSurname.getStyleClass().removeAll("poljeNijeIspravno");
                fldSurname.getStyleClass().add("poljeIspravno");
            } else {
                fldSurname.getStyleClass().removeAll("poljeIspravno");
                fldSurname.getStyleClass().add("poljeNijeIspravno");
            }
        });


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

        fldPass.textProperty().addListener((obs, oldIme, newIme) -> {
            AdminAccount userAccount = new AdminAccount("","","",newIme);

            if (!newIme.isEmpty() && newIme.equals(userAccount.checkPassword())){

                fldPass.getStyleClass().removeAll("poljeNijeIspravno");
                fldPass.getStyleClass().add("poljeIspravno");

            } else {
                fldPass.getStyleClass().removeAll("poljeIspravno");
                fldPass.getStyleClass().add("poljeNijeIspravno");

            }

        });
    }

  

}




