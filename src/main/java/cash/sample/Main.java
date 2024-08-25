package cash.sample;


import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
/**
 * @author Lewis Perry Nickname = Slippy
 *
 * @version 0.01
 *
 * */

public  class Main extends Application {






    public static void main(String[] args) {


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






