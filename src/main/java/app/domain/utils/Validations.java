package app.domain.utils;

import app.interfaces.CONSTANT;


import java.time.LocalDate;

/**
 * @authors Daniel Aires, Fernando Ribeiro, José Silva, Manuel Marques, Mariana Rocha
 */
public abstract class Validations implements CONSTANT {

    public static boolean validString(String s) {
        if(s == null || s.length() == 0) {
            return false;
        }
        if(s.length() > CONSTANT.MAXSTRINGSIZE) {
            return false;
        }
        return true;
    }

    public static boolean validLocID(String s) {
        if(s == null || s.length() == 0) {
            return false;
        }
        String subLocID=s.substring(0,2);
        if(!subLocID.equals(CONSTANT.LOCID)) {
            return false;
        }
        try {
            Integer.parseInt(s.substring(2));
        }
        catch(NumberFormatException e) {
            return false;
        }

        return true;
    }
    public static boolean validEntity(String s) {
        if(s == null || s.length() == 0) {
            return false;
        }
        if(s.charAt(0)!=Client && s.charAt(0)!=Produtor && s.charAt(0)!=Company) {
            return false;
        }

        String number = s.substring(1);
        int validNumber;
        try {
            validNumber = Integer.parseInt(number);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean validLatitude(String latitude) {
        double codeValid = 0;
        try {
            codeValid = Double.parseDouble(latitude);
        }
        catch(NumberFormatException e) {
            return false;
        }
        if(codeValid < -90 || codeValid > 90) {
            return false;
        }
        return true;
    }

    public static boolean validLongitude(String longitude) {
        double codeValid = 0;
        try {
            codeValid = Double.parseDouble(longitude);
        }
        catch(NumberFormatException e) {
            return false;
        }
        if(codeValid < -180 || codeValid > 180) {
            return false;
        }
        return true;
    }

    public static boolean validDistance(String distance) {
        int q = 0;
        try {
            q = Integer.parseInt(distance);
        }
        catch(NumberFormatException e) {
            return false;
        }
        if(q <= 0 || q > CONSTANT.MAXDISTANCE) {
            return false;
        }
        return true;
    }


}
