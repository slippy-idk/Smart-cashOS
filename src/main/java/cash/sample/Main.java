package cash.sample;


import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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

        //tbd check if can start connection of sql databse here and remove repating code

        launch(args);
    }



    public  void start(Stage Main_Menu) throws IOException { //this is the start of the gui and login menu
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        Main_Menu.setTitle("Main Menu");
        Main_Menu.setScene(scene);
        Main_Menu.show();
    }



    }






