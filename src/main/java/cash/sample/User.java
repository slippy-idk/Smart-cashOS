package cash.sample;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    //    public static void View_Account(String name) {
//
//        System.out.println("Please enter the name of the Employee your trying to view");
//
//
//
//        try (FileInputStream fileInputStream = new FileInputStream("Users.txt")) {
//
//
//            do {
//                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//
//                User Temp_User = (User) objectInputStream.readObject();
//                if (Temp_User.Name.equals(name.toLowerCase())) {
//                    System.out.println(Temp_User);
//
//                }
//            } while (true);
//
//        } catch (IOException e) {
//            System.out.println("The account was not found");
//
//            //tbd put accout not found
//
//
//        } catch (ClassNotFoundException e) {
//            System.out.println("cash.sample.User Error 3: Class not found"); //if this fails than that means a Non cash.sample.User class has been found and selected
//
//
//        }
//
//
//    }










    public static void View_Account(User user){


        Stage Self_view = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(User.class.getResource("Self_View_account.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 620, 640);
        } catch (IOException w) {
            throw new RuntimeException(w);
        }
        Self_view.setTitle("View Account");
        Self_view.setScene(scene);
        Self_view.show();



    }


    public void End_shift(User User, long Start_Shift) {
//        long End_Shift = System.currentTimeMillis();
//
//
//        End_Shift = End_Shift - Start_Shift;
//
//        long Hours = End_Shift/1000;
//        Hours = Hours/60;
//        Hours = Hours/60;
//
//        User.setHoursWorked(Hours);
//
//        System.out.println("You have worked this amount of Hours");
//        System.out.println(User.getHoursWorked());
//
//        System.out.println("You Gross Earnings is");
//        System.out.println(User.getGrossEarning());
//
//
//        System.out.println(" "); // used to space menu and the end shift screen


        //tbd put in gui for displaying end

        //put all this on screen




    }
}