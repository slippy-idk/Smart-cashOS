package cash.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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


public User Current_User;


    public static void Create_Account(String name) { //used to create a user account
        try (FileOutputStream fileOutputStream = new FileOutputStream("Users.txt", true)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);


            System.out.println("Please Enter the name of the employee");

            User Temp_User = new User(name);


            objectOutputStream.writeObject(Temp_User);
            System.out.println("Successfully Created New Employee Account");
        } catch (IOException e) {
            System.out.println("cash.sample.User Error 1: failed to save cash.sample.User IO or Runtime error Occurred");

        }



    }


    public static void View_Account(String name) {

        System.out.println("Please enter the name of the Employee your trying to view");



        try (FileInputStream fileInputStream = new FileInputStream("Users.txt")) {


            do {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                User Temp_User = (User) objectInputStream.readObject();
                if (Temp_User.Name.equals(name.toLowerCase())) {
                    System.out.println(Temp_User);

                }
            } while (true);

        } catch (IOException e) {
            System.out.println("The account was not found");
            Main.Administrator(); //returns cash.sample.User On error

        } catch (ClassNotFoundException e) {
            System.out.println("cash.sample.User Error 3: Class not found"); //if this fails than that means a Non cash.sample.User class has been found and selected


        }


    }




    public static void Login() { //load the login screen
        Stage stage = new Stage();




        FXMLLoader fxmlLoader = new FXMLLoader(User.class.getResource("Staff_Login.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 620, 640);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Staff Login");
        stage.setScene(scene);
        stage.show();

        //the actuall login and checker for account is in the controller class for the sake of keeping current track of the User account


    }










    public  void employe_Start(User user) throws IOException { //this is the start of the gui and login menu

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Staff_Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        stage.setTitle("Hello");
        stage.setScene(scene);
        stage.show();





    }



public static void Start_Sales() throws IOException {

    Stage stage = new Stage();
    FXMLLoader fxmlLoader = new FXMLLoader(User.class.getResource("Sales.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 620, 640);
    stage.setTitle("Hello");
    stage.setScene(scene);
    stage.show();

}






    public void End_shift(User User, long Start_Shift) {
        long End_Shift = System.currentTimeMillis();


        End_Shift = End_Shift - Start_Shift;

        long Hours = End_Shift / 3600000;


        User.setHoursWorked(Hours);

        System.out.println("You have worked this amount of Hours");
        System.out.println(User.getHoursWorked());

        System.out.println("You Gross Earnings is");
        System.out.println(User.getGrossEarning());


        System.out.println(" "); // used to space menu and the end shift screen
        Main.Menu();//returns the cash.sample.User to the Menu to not trigger an Io error


    }


    public void start(Stage stage) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Staff_Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void close(Stage stage){
        stage.close();
    }
}