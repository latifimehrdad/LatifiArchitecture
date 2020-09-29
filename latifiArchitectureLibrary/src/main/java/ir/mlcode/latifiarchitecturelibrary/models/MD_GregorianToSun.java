package ir.mlcode.latifiarchitecturelibrary.models;

public class MD_GregorianToSun {

    private int intYear;
    private int intMonth;
    private int intDay;
    private String stringMonth;
    private String stringDay;
    private String dayOfWeek;
    private String monthOfYear;
    private String stringYear;


    public MD_GregorianToSun() {

    }


    public String getFullStringSun() {
        return getDayOfWeek() + " " + getStringDay() + " " + getMonthOfYear() + " " + getIntYear();
    }

    public int getIntYear() {
        return intYear;
    }

    public void setIntYear(int intYear) {
        this.intYear = intYear;
    }

    public int getIntMonth() {
        return intMonth;
    }

    public void setIntMonth(int intMonth) {
        this.intMonth = intMonth;
    }

    public int getIntDay() {
        return intDay;
    }

    public void setIntDay(int intDay) {
        this.intDay = intDay;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getMonthOfYear() {
        return monthOfYear;
    }

    public void setMonthOfYear(String monthOfYear) {
        this.monthOfYear = monthOfYear;
    }

    public String getStringMonth() {
        return stringMonth;
    }

    public void setStringMonth(String stringMonth) {
        this.stringMonth = stringMonth;
    }

    public String getStringDay() {
        return stringDay;
    }

    public void setStringDay(String stringDay) {
        this.stringDay = stringDay;
    }

    public String getStringYear() {
        return stringYear;
    }

    public void setStringYear(String stringYear) {
        this.stringYear = stringYear;
    }
}