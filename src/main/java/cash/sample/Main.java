package cash.sample;


import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import java.sql.*;


import java.io.IOException;
import java.util.Scanner;
/**
 * @author Lewis Perry Nickname = Slippy
 *
 * @version 0.01
 *
 * */

public  class Main extends Application {



    static Scanner User_Input = new Scanner(System.in);
    static String input;
    //these two global variables are designed for user input

    public static void main(String[] args) {
//        launch(args);


        Main main = new Main();

        main.test();








    }

    public static void Menu(){

        System.out.println("What user would you like to login as");
        System.out.println("1: Employee");
        System.out.println("2: Administrator");
        String input = User_Input.nextLine();



        switch (input){
            case "1":

                cash.sample.User.Login();



            case "2":
                Administrator();

            default:
                System.out.println("You entered A wrong input try again");
                Menu();

        }

    }


    public static void Administrator(){

        System.out.println("What would you like to do");
        System.out.println("1: Create An employee Account");
        System.out.println("2: View An employee Account");
        System.out.println("3: Back out");

        input = User_Input.nextLine();

        switch (input){
            case "1":
                input = User_Input.nextLine();
                cash.sample.User.Create_Account(input);

            case "2":
                input = User_Input.nextLine();
                cash.sample.User.View_Account(input);

            case "3":
                Menu();

            default:
                System.out.println("You entered an incorrect Input try again");
                Administrator();
        }

    }


    public  void start(Stage Main_Menu) throws IOException { //this is the start of the gui and login menu
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        Main_Menu.setTitle("Main Menu");
        Main_Menu.setScene(scene);
        Main_Menu.show();
    }

    public void test(){

        try{


        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/test",
                "root",
                "1234"
        );

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM USER");

        while(resultSet.next()){
            System.out.println(resultSet.getString("idUser"));
            System.out.println(resultSet.getString("Name"));
        };
    }catch (SQLException e){
            System.out.println("Error");
        }


    }






}