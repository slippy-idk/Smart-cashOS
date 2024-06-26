package cash.sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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


    public static void Create_Account() { //used to create a user account
        try (FileOutputStream fileOutputStream = new FileOutputStream("Users.txt", true)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);


            System.out.println("Please Enter the name of the employee");
            Main.input = Main.User_Input.nextLine();
            User Temp_User = new User(Main.input.toLowerCase());


            objectOutputStream.writeObject(Temp_User);
            System.out.println("Successfully Created New Employee Account");
        } catch (IOException e) {
            System.out.println("cash.sample.User Error 1: failed to save cash.sample.User IO or Runtime error Occurred");
            Main.Administrator();//returns user on Error
            throw new RuntimeException(e);
        }

        Main.Administrator();//returns cash.sample.User without risk of triggering Other Functions
    }


    public static void View_Account() {

        System.out.println("Please enter the name of the Employee your trying to view");
        String name = Main.User_Input.nextLine();


        try (FileInputStream fileInputStream = new FileInputStream("Users.txt")) {


            do {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                User Temp_User = (User) objectInputStream.readObject();
                if (Temp_User.Name.equals(name.toLowerCase())) {
                    System.out.println(Temp_User);
                    Main.Administrator();
                }
            } while (true);

        } catch (IOException e) {
            System.out.println("The account was not found");
            Main.Administrator(); //returns cash.sample.User On error
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("cash.sample.User Error 3: Class not found"); //if this fails than that means a Non cash.sample.User class has been found and selected
            Main.Administrator(); //returns cash.sample.User On error
            throw new RuntimeException(e);

        }


    }

    public static void Login() { //why is this given me an error when is in use tbd
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


    }



    public static void login_Check(String name){




        try (FileInputStream fileInputStream = new FileInputStream("Users.txt")) {


            do {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                User Temp_User = (User) objectInputStream.readObject();
                if (Temp_User.Name.equals(name.toLowerCase())) {
                    long Start_Shift = System.currentTimeMillis();
                    Temp_User.employee_Start(Temp_User, Start_Shift);
                }
            } while (true);

        } catch (IOException e) {
            System.out.println("The account was not found");
            Main.Menu(); //returns cash.sample.User On error
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("cash.sample.User Error 3: Class not found"); //if this fails than that means a Non cash.sample.User class has been found and selected
            Main.Menu(); //returns cash.sample.User On error
            throw new RuntimeException(e);

        }

    }

    public void employee_Start(User User, long Start_Shift) {


        System.out.println("What would you like to do");
        System.out.println("1: Start Sale");
        System.out.println("END: end your shift on console");

        Main.input = Main.User_Input.nextLine();


        switch (Main.input.toLowerCase()) {
            case "end":
                End_shift(User, Start_Shift);


            case "1":
                do {
                    System.out.println(" "); // used to space out for readability
                    System.out.println("What is the Amount of Money Paid");
                    System.out.println("Enter 0 to END sales");
                    double Payment = Main.User_Input.nextDouble();

                    if (Payment == 0) {
                        employee_Start(User, Start_Shift);
                    }

                    User.setGrossEarning(User.getGrossEarning() + Payment);


                } while (true);


            default:
                System.out.println("You entered the Wrong character try again");
                employee_Start(User, Start_Shift);


        }

    }


    public void End_shift(User User, long Start_Shift) {
        long End_Shift = System.currentTimeMillis();


        End_Shift = End_Shift - Start_Shift;

        long Hours = End_Shift / 1000;
        Hours = Hours / 60;
        Hours = Hours / 60;

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