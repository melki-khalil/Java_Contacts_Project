package API;

import java.sql.Date;
import java.sql.Time;

public class RecentCall {
    private int idC;
    private int idA;
    private String name;
    private String number;
    private Date callDate;
    private Time callTime;
    private String countryCode;
    private byte[] image;

    // Getters and Setters
    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public Time getCallTime() {
        return callTime;
    }

    public void setCallTime(Time callTime) {
        this.callTime = callTime;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
