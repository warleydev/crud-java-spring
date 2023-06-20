package com.warleydev.desafionelio.services.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LicensePlateValidate {
    public static boolean isValid(String license){

        if (license.length() > 0) {
            if (license.length() < 7) {
                return false;
            } else {
                if (!license.matches("[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}|[A-Z]{3}[0-9]{4}")) {
                   return false;
                }
                else{
                    return true;
                }
            }
        }
        return false;
    }

}
