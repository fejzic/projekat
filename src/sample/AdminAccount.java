package sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminAccount extends UserAccount {

    public AdminAccount(String firstName, String lastName, String userName, String password) {
        super(firstName, lastName, userName, password);
    }

    public AdminAccount() {
    }

    public boolean checkPassword(){ 
        String pass = super.getPassword();
        String pattern = "(?=.*[A-Z])(?=.*[a-z])(?=.*?[0-9])(?=.*[@#$%^&+=*;.<>()ß¤×÷¸¸¨˘°°!:˛`˙˘ˇ~/])";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(pass);

        return m.find();
    }
}
