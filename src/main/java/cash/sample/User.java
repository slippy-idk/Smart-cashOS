package cash.sample;



import java.io.*;

public class User implements Serializable {

    private String Name;

    private long HoursWorked;

    private double GrossEarning;

    public String getName() {
        return Name;
    }

    public long getHoursWorked() {
        return HoursWorked;
    }

    public double getGrossEarning() {
        return GrossEarning;
    }

    public User(String name) {
        Name = name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setHoursWorked(long hoursWorked) {
        HoursWorked = hoursWorked;


    }

    public void setGrossEarning(double grossEarning) {
        GrossEarning = grossEarning;
    }

    private String User_Rights;

    public String getUser_Rights() {
        return User_Rights;
    }

    public void setUser_Rights(String user_Rights) {
        User_Rights = user_Rights;
    }


}