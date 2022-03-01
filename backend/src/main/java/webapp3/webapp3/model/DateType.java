package webapp3.webapp3.model;

import java.io.Serializable;

public class DateType implements Serializable {

    private String year;
    private String month;
    private String day;
    private String spanishFormat;

    public DateType(){

    }

    public DateType(String year, String month, String day){
        super();
        this.year = year;
        this.month = month;
        this.day = day;
        this.spanishFormat = day + "/" + month + "/" + year;
    }

    @Override
    public String toString(){
        return year + "-" + month + "-" + day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSpanishFormat() {
        return spanishFormat;
    }

    public void setSpanishFormat(String spanishFormat) {
        this.spanishFormat = spanishFormat;
    }

    public void generateSpanishFormat() {
        this.spanishFormat = day + "/" + month + "/" + year;
    }
}
