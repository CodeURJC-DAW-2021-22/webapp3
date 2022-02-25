package webapp3.webapp3.model;

import java.io.Serializable;

public class DateType implements Serializable {

    private String year;
    private String month;
    private String day;

    public DateType(){

    }

    public DateType(String day, String month, String year){
        super();
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString(){
        return day + "/" + month + "/" + year;
    }
}
