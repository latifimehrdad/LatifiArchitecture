package ir.mlcode.latifiarchitecturelibrary.utility;

import android.util.Patterns;

public class Validations {

    //______________________________________________________________________________________________ Validations
    public Validations() {
    }
    //______________________________________________________________________________________________ Validations

    //______________________________________________________________________________________________ mobileValidation
    public boolean mobileValidation(String mobile) {

        if (mobile == null)
            return false;

        if (mobile.isEmpty())
            return false;

        if (mobile.length() < 11)
            return false;

        String zeroNine = mobile.subSequence(0, 2).toString();
        if (!zeroNine.equalsIgnoreCase("09"))
            return false;

        return true;

    }
    //______________________________________________________________________________________________ mobileValidation


    //______________________________________________________________________________________________ textValidation
    public boolean textValidation(String text) {

        if (text == null)
            return false;

        if (text.isEmpty())
            return false;

        return true;
    }
    //______________________________________________________________________________________________ textValidation


    //______________________________________________________________________________________________ emailValidation
    public boolean emailValidation(String email) {

        if (email == null)
            return false;

        if (email.isEmpty())
            return false;

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }
    //______________________________________________________________________________________________ emailValidation
}
