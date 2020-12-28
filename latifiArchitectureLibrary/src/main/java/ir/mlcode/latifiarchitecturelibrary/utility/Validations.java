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



    //______________________________________________________________________________________________ nationalValidation
    public boolean nationalValidation(String national) {

        if (national == null)
            return false;

        if (national.isEmpty())
            return false;

        if (national.length() != 10)
            return false;

        long nationalCode = Long.parseLong(national);
        byte[] arrayNationalCode = new byte[10];

        //extract digits from number
        for (int i = 0; i < 10 ; i++) {
            arrayNationalCode[i] = (byte) (nationalCode % 10);
            nationalCode = nationalCode / 10;
        }

        //Checking the control digit
        int sum = 0;
        for (int i = 9; i > 0 ; i--)
            sum += arrayNationalCode[i] * (i+1);
        int temp = sum % 11;
        if (temp < 2)
            return arrayNationalCode[0] == temp;
        else
            return arrayNationalCode[0] == 11 - temp;

    }
    //______________________________________________________________________________________________ nationalValidation


}
