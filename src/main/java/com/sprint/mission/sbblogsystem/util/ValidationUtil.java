package com.sprint.mission.sbblogsystem.util;

import java.util.regex.Pattern;

public class ValidationUtil {
    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");


    public static boolean isValidId(String id){
        return id != null && id.length() >= 6 && id.length() <= 30;
    }

    public static boolean isValidPassword(String password){
        if(password == null || password.length() < 12 || password.length()>50) {
            return false;
        }

        int letters = 0 ;
        int digits = 0 ;
        int specialChars = 0 ;

        for ( char ch : password.toCharArray()){
            if (Character.isLetter(ch)) letters++;
            else if(Character.isDigit(ch)) digits++;
            else if("!@#$%^&*".indexOf(ch) != -1) specialChars++;
        }

        return letters >= 2 && digits >= 2 && specialChars >= 2;
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.length() <= 100 && EMAIL_REGEX.matcher(email).matches();
    }

    public static boolean isValidNickname(String nickname) {
        return nickname != null && nickname.length() <= 50;
    }
}
