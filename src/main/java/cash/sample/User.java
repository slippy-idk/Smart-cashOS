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











//    public void End_shift(User User, long Start_Shift) {
////        long End_Shift = System.currentTimeMillis();
////
////
////        End_Shift = End_Shift - Start_Shift;
////
////        long Hours = End_Shift/1000;
////        Hours = Hours/60;
////        Hours = Hours/60;
////
////        User.setHoursWorked(Hours);
////
////        System.out.println("You have worked this amount of Hours");
////        System.out.println(User.getHoursWorked());
////
////        System.out.println("You Gross Earnings is");
////        System.out.println(User.getGrossEarning());
////
////
////        System.out.println(" "); // used to space menu and the end shift screen
//
//
//        //tbd put in gui for displaying end
//
//        //put all this on screen
//
//
//
//
//    }
}