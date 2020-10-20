package ir.mlcode.latifiarchitecturelibrary.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MD_GregorianDate {

    int year;

    int month;

    int day;

    public MD_GregorianDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }


    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }


    public String getDateString() {
        Locale loc = new Locale("en_US");
        String date = year + "/" + String.format(loc, "%02d", month) + "/" + String.format(loc, "%02d", day);
        return date;
    }



    public Date getDate() {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date d = sdf.parse(getDateString());
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
