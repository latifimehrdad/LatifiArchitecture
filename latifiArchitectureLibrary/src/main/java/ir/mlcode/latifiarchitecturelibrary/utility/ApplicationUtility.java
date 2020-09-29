package ir.mlcode.latifiarchitecturelibrary.utility;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import ir.mlcode.latifiarchitecturelibrary.models.MD_GregorianToSun;

public class ApplicationUtility {


    private Validations validations;


    //______________________________________________________________________________________________ ApplicationUtility
    public ApplicationUtility() {
        validations = new Validations();
    }
    //______________________________________________________________________________________________ ApplicationUtility


    //______________________________________________________________________________________________ persianToEnglish
    public String persianToEnglish(String persianStr) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < persianStr.length(); i++) {
            char c = persianStr.charAt(i);
            switch (c) {
                case '۰':
                    result.append("0");
                    break;
                case '۱':
                    result.append("1");
                    break;
                case '۲':
                    result.append("2");
                    break;
                case '۳':
                    result.append("3");
                    break;
                case '۴':
                    result.append("4");
                    break;
                case '۵':
                    result.append("5");
                    break;
                case '۶':
                    result.append("6");
                    break;
                case '۷':
                    result.append("7");
                    break;
                case '۸':
                    result.append("8");
                    break;
                case '۹':
                    result.append("9");
                    break;
                default:
                    result.append(c);
                    break;
            }
        }

        return result.toString();
    }
    //______________________________________________________________________________________________ persianToEnglish


    //______________________________________________________________________________________________ gregorianToSun
    public MD_GregorianToSun gregorianToSun(Date GregorianDate) {

        //Type = "FullJalaliNumber = 1367/05/31"
        //Type = "YearJalaliNumber = 1367"
        //Type = "MonthJalaliNumber = 05"
        //Type = "DayJalaliNumber = 31"
        //Type = "FullJalaliString = پنجشنبه 31 مرداد 1367"
        //Type = "MonthJalaliString = مرداد"
        //Type = "DayJalaliString = پنجشنبه"

        String strWeekDay = "";
        String strMonth = "";

        int date;
        int month;
        int year;

/*        int gregYear = c.get(Calendar.YEAR) + 1900;
        int gregMonth = c.get(Calendar.MONTH) + 1;
        int gregDate = c.get(Calendar.DATE);
        int WeekDay = c.get(Calendar.DAY_OF_WEEK);*/

/*        int gregYear = GregorianDate.getYear() + 1900;
        int gregMonth = GregorianDate.getMonth() + 1;
        int gregDate = GregorianDate.getDate();
        int WeekDay = GregorianDate.getDay();*/

        int ld;

        int miladiYear = GregorianDate.getYear() + 1900;
        int miladiMonth = GregorianDate.getMonth() + 1;
        int miladiDate = GregorianDate.getDate();
        int WeekDay = GregorianDate.getDay();

        int[] buf1 = new int[12];
        int[] buf2 = new int[12];

        buf1[0] = 0;
        buf1[1] = 31;
        buf1[2] = 59;
        buf1[3] = 90;
        buf1[4] = 120;
        buf1[5] = 151;
        buf1[6] = 181;
        buf1[7] = 212;
        buf1[8] = 243;
        buf1[9] = 273;
        buf1[10] = 304;
        buf1[11] = 334;

        buf2[0] = 0;
        buf2[1] = 31;
        buf2[2] = 60;
        buf2[3] = 91;
        buf2[4] = 121;
        buf2[5] = 152;
        buf2[6] = 182;
        buf2[7] = 213;
        buf2[8] = 244;
        buf2[9] = 274;
        buf2[10] = 305;
        buf2[11] = 335;

        if ((miladiYear % 4) != 0) {
            date = buf1[miladiMonth - 1] + miladiDate;

            if (date > 79) {
                date = date - 79;
                if (date <= 186) {
                    switch (date % 31) {
                        case 0:
                            month = date / 31;
                            date = 31;
                            break;
                        default:
                            month = (date / 31) + 1;
                            date = (date % 31);
                            break;
                    }
                    year = miladiYear - 621;
                } else {
                    date = date - 186;

                    switch (date % 30) {
                        case 0:
                            month = (date / 30) + 6;
                            date = 30;
                            break;
                        default:
                            month = (date / 30) + 7;
                            date = (date % 30);
                            break;
                    }
                    year = miladiYear - 621;
                }
            } else {
                if ((miladiYear > 1996) && (miladiYear % 4) == 1) {
                    ld = 11;
                } else {
                    ld = 10;
                }
                date = date + ld;

                switch (date % 30) {
                    case 0:
                        month = (date / 30) + 9;
                        date = 30;
                        break;
                    default:
                        month = (date / 30) + 10;
                        date = (date % 30);
                        break;
                }
                year = miladiYear - 622;
            }
        } else {
            date = buf2[miladiMonth - 1] + miladiDate;

            if (miladiYear >= 1996) {
                ld = 79;
            } else {
                ld = 80;
            }
            if (date > ld) {
                date = date - ld;

                if (date <= 186) {
                    switch (date % 31) {
                        case 0:
                            month = (date / 31);
                            date = 31;
                            break;
                        default:
                            month = (date / 31) + 1;
                            date = (date % 31);
                            break;
                    }
                    year = miladiYear - 621;
                } else {
                    date = date - 186;

                    switch (date % 30) {
                        case 0:
                            month = (date / 30) + 6;
                            date = 30;
                            break;
                        default:
                            month = (date / 30) + 7;
                            date = (date % 30);
                            break;
                    }
                    year = miladiYear - 621;
                }
            } else {
                date = date + 10;

                switch (date % 30) {
                    case 0:
                        month = (date / 30) + 9;
                        date = 30;
                        break;
                    default:
                        month = (date / 30) + 10;
                        date = (date % 30);
                        break;
                }
                year = miladiYear - 622;
            }

        }


        switch (month) {
            case 1:
                strMonth = "فروردين";
                break;
            case 2:
                strMonth = "ارديبهشت";
                break;
            case 3:
                strMonth = "خرداد";
                break;
            case 4:
                strMonth = "تير";
                break;
            case 5:
                strMonth = "مرداد";
                break;
            case 6:
                strMonth = "شهريور";
                break;
            case 7:
                strMonth = "مهر";
                break;
            case 8:
                strMonth = "آبان";
                break;
            case 9:
                strMonth = "آذر";
                break;
            case 10:
                strMonth = "دي";
                break;
            case 11:
                strMonth = "بهمن";
                break;
            case 12:
                strMonth = "اسفند";
                break;
        }

        switch (WeekDay) {

            case 0:
                strWeekDay = "يکشنبه";
                break;
            case 1:
                strWeekDay = "دوشنبه";
                break;
            case 2:
                strWeekDay = "سه شنبه";
                break;
            case 3:
                strWeekDay = "چهارشنبه";
                break;
            case 4:
                strWeekDay = "پنج شنبه";
                break;
            case 5:
                strWeekDay = "جمعه";
                break;
            case 6:
                strWeekDay = "شنبه";
                break;
        }


        Locale loc = new Locale("en_US");
        MD_GregorianToSun gregorianToSun = new MD_GregorianToSun();
        gregorianToSun.setStringYear(String.valueOf(year));
        gregorianToSun.setIntYear(year);
        gregorianToSun.setIntMonth(month);
        gregorianToSun.setIntDay(date);
        gregorianToSun.setDayOfWeek(strWeekDay);
        gregorianToSun.setMonthOfYear(strMonth);
        gregorianToSun.setStringMonth(String.format(loc, "%02d", month));
        gregorianToSun.setStringDay(String.format(loc, "%02d", date));

        return gregorianToSun;
    }
    //______________________________________________________________________________________________ gregorianToSun


    //______________________________________________________________________________________________ customToastShow
    public void customToastShow(Context context, String message, int color, int textColor) {

        //context.getResources().getColor(R.color.mlWhite)
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        View view = toast.getView();
        view.setBackgroundColor(color);
        TextView text = (TextView) view.findViewById(android.R.id.message);
        text.setPadding(10, 2, 10, 2);
        text.setTextColor(context.getResources().getColor(textColor));
        text.setTextSize(2, 17.0f);
        text.setGravity(17);
        toast.setGravity(17, 0, 0);
        toast.show();
    }
    //______________________________________________________________________________________________ customToastShow


    //______________________________________________________________________________________________ setTextWatcherSplitting
    public TextWatcher setTextWatcherSplitting(final EditText editText, final double amount) {

        final String[] value = new String[2];

        return new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                value[0] = editText.getText().toString();
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                value[1] = charSequence.toString();
            }

            public void afterTextChanged(Editable editable) {

                if (value[0] == null || value[0].equalsIgnoreCase(""))
                    value[0] = "0";

                if (value[1] == null || value[1].equalsIgnoreCase(""))
                    value[1] = "0";

                value[0] = value[0].replaceAll(",", "");
                value[0] = value[0].replaceAll("٬", "");

                value[1] = value[1].replaceAll(",", "");
                value[1] = value[1].replaceAll("٬", "");


                if (value[1].equalsIgnoreCase("0")) {
                    editText.removeTextChangedListener(this);
                    editText.setText("");
                    editText.addTextChangedListener(this);
                    return;
                }


                if (amount >= Long.valueOf(value[1])) {
                    String m = value[1];
                    m = m.replaceAll(",", "");
                    m = m.replaceAll("٬", "");
                    if (!m.equalsIgnoreCase("")) {
                        editText.removeTextChangedListener(this);
                        editText.setText(splitNumberOfAmount(Long.valueOf(m)));
                        editText.addTextChangedListener(this);
                    }
                } else {
                    editText.removeTextChangedListener(this);
                    editText.setText(splitNumberOfAmount(Long.valueOf(value[0])));
                    editText.addTextChangedListener(this);
                }


                editText.setSelection(editText.getText().length());

            }
        };
    }
    //______________________________________________________________________________________________ setTextWatcherSplitting


    //______________________________________________________________________________________________ splitNumberOfAmount
    public String splitNumberOfAmount(Long amount) {
        NumberFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }
    //______________________________________________________________________________________________ splitNumberOfAmount


    //______________________________________________________________________________________________ jalaliDatBetween
    public Integer jalaliDatBetween(String Date1, String Date2, Integer intDate1, Integer intDate2) {
        Integer DateStart;
        Integer DateEnd;
        int c1;
        int b2;
        int c2;
        int b1;
        int a1;
        int a2;
        if (intDate1 != null) {
            DateStart = intDate1;
            DateEnd = intDate2;
        } else if (Date1.length() != 10) {
            return 0;
        } else {
            DateStart = Integer.valueOf(Date1.replaceAll("/", ""));
            DateEnd = Integer.valueOf(Date2.replaceAll("/", ""));
        }
        if (DateStart == 0 || DateEnd == 0) {
            return 0;
        }
        String DateStartString = String.valueOf(DateStart);
        String DateEndString = String.valueOf(DateEnd);
        if (DateStart > DateEnd) {
            a1 = Integer.parseInt(DateStartString.substring(0, 4));
            b1 = Integer.parseInt(DateStartString.substring(4, 6));
            c1 = Integer.parseInt(DateStartString.substring(6, 8));
            a2 = Integer.parseInt(DateEndString.substring(0, 4));
            b2 = Integer.parseInt(DateEndString.substring(4, 6));
            c2 = Integer.parseInt(DateEndString.substring(6, 8));
        } else {
            a1 = Integer.parseInt(DateEndString.substring(0, 4));
            b1 = Integer.parseInt(DateEndString.substring(4, 6));
            c1 = Integer.parseInt(DateEndString.substring(6, 8));
            a2 = Integer.parseInt(DateStartString.substring(0, 4));
            b2 = Integer.parseInt(DateStartString.substring(4, 6));
            c2 = Integer.parseInt(DateStartString.substring(6, 8));
        }
        int B = 0;
        int d = b2;
        while (a2 < a1) {
            while (d <= 12) {
                B += switchYear(d, a2);
                d++;
            }
            a2++;
            d = 1;
        }
        while (d < b1) {
            B += switchYear(d, a1);
            d++;
        }
        return (B + c1) - c2;
    }
    //______________________________________________________________________________________________ jalaliDatBetween


    //______________________________________________________________________________________________ jalaliAddDay
    public String jalaliAddDay(String Date1, Integer intDate1, int day) {
        Integer DateStart;
        if (intDate1 != null) {
            DateStart = intDate1;
        } else if (Date1.length() != 10) {
            return null;
        } else {
            DateStart = Integer.valueOf(Date1.replaceAll("/", ""));
        }
        if (DateStart == 0) {
            return null;
        }
        int a1 = Integer.parseInt(String.valueOf(DateStart).substring(0, 4));
        int b1 = Integer.parseInt(String.valueOf(DateStart).substring(4, 6));
        int c1 = Integer.parseInt(String.valueOf(DateStart).substring(6, 8));
        int day2 = day + c1;
        while (day2 > 0) {
            int temp = switchYear(b1, a1);
            if (day2 >= temp) {
                day2 -= temp;
                b1++;
                c1 = 0;
            } else {
                c1 = day2;
                day2 = 0;
            }
            if (b1 > 12) {
                a1++;
                b1 = 1;
            }
        }

        if (c1 == 0) {
            b1--;
            c1 = switchYear(b1, a1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(a1));
        sb.append("/");
        String str2 = "%02d";
        sb.append(String.format(str2, new Object[]{Integer.valueOf(b1)}));
        sb.append("/");
        sb.append(String.format(str2, new Object[]{Integer.valueOf(c1)}));
        return sb.toString();
    }
    //______________________________________________________________________________________________ jalaliAddDay


    //______________________________________________________________________________________________ jalaliReduceDay
    public String jalaliReduceDay(String Date1, Integer intDate1, int day) {
        Integer DateStart;
        if (intDate1 != null) {
            DateStart = intDate1;
        } else if (Date1.length() != 10 || Date1.length() != 10) {
            return null;
        } else {
            DateStart = Integer.valueOf(Date1.replaceAll("/", ""));
        }
        if (DateStart.intValue() == 0) {
            return null;
        }
        int a1 = Integer.parseInt(String.valueOf(DateStart).substring(0, 4));
        int b1 = Integer.parseInt(String.valueOf(DateStart).substring(4, 6));
        int day2 = day - Integer.parseInt(String.valueOf(DateStart).substring(6, 8));
        while (day2 > 0) {
            b1--;
            day2 -= switchYear(b1, a1);
            if (b1 == 1) {
                b1 = 13;
                a1--;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(a1));
        sb.append("/");
        String str2 = "%02d";
        sb.append(String.format(str2, new Object[]{Integer.valueOf(b1)}));
        sb.append("/");
        if (day2 < 0)
            sb.append(String.format(str2, new Object[]{Integer.valueOf(day2 * -1)}));
        else
            sb.append("01");
        return sb.toString();
    }
    //______________________________________________________________________________________________ jalaliReduceDay


    //______________________________________________________________________________________________ switchYear
    private int switchYear(int d, int year) {
        switch (d) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return 31;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return 30;
            case 12:
                if (kabise(year)) {
                    return 30;
                }
                return 29;
            default:
                return 0;
        }
    }
    //______________________________________________________________________________________________ switchYear


    //______________________________________________________________________________________________ kabise
    private Boolean kabise(int year) {
        int temp = year % 33;
        if (temp == 1 || temp == 5 || temp == 9 || temp == 13 || temp == 17 || temp == 22 || temp == 26 || temp == 30) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    //______________________________________________________________________________________________ kabise


    //______________________________________________________________________________________________ setRoundCircleImage
    public void setRoundCircleImage(SimpleDraweeView simpleDraweeView, int borderColor, float borderWidth) {
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setBorder(borderColor, borderWidth);
        roundingParams.setRoundAsCircle(true);
        simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
    }
    //______________________________________________________________________________________________ setRoundCircleImage


    //______________________________________________________________________________________________ setRoundImage
    public void setRoundImage(SimpleDraweeView simpleDraweeView, int borderColor, float borderWidth, float topLeft, float topRight, float bottomLeft, float bottomRight) {
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setBorder(borderColor, borderWidth);
        roundingParams.setCornersRadii(topLeft, topRight, bottomRight, bottomLeft);
        simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
    }
    //______________________________________________________________________________________________ setRoundImage


    //______________________________________________________________________________________________ setProgressBarForLoadImage
    public void setProgressBarForLoadImage(SimpleDraweeView simpleDraweeView, int barColor, int backColor, int radius) {
        ProgressBarDrawable progressBarDrawable = new ProgressBarDrawable();
        progressBarDrawable.setColor(barColor);
        progressBarDrawable.setBackgroundColor(backColor);
        progressBarDrawable.setRadius(radius);
        simpleDraweeView.getHierarchy().setProgressBarImage(progressBarDrawable);
    }
    //______________________________________________________________________________________________ setProgressBarForLoadImage


    //______________________________________________________________________________________________ getValidations
    public Validations getValidations() {
        return validations;
    }
    //______________________________________________________________________________________________ getValidations

}
