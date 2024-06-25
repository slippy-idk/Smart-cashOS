package cash.sample;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Scanner;
/**
 * @author Lewis Perry Nickname = Slippy
 *
 * @version 0.01
 *
 * */

public class Main extends Application {



    static Scanner User_Input = new Scanner(System.in);
    static String input;
    //these two global variables are designed for user input

    public static void main(String[] args) {
        launch(args);








    }

    public static void Menu(){

        System.out.println("What user would you like to login as");
        System.out.println("1: Employee");
        System.out.println("2: Administrator");
        String input = User_Input.nextLine();



        switch (input){
            case "1":
                //       User.Login();



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
//                User.Create_Account();

            case "2":
//                User.View_Account();

            case "3":
                Menu();

            default:
                System.out.println("You entered an incorrect Input try again");
                Administrator();
        }

    }


    public void start(Stage stage) throws IOException { //this is the start of the gui and login menu
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


    }



}