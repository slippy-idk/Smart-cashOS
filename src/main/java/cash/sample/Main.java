package cash.sample;


import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
/**
 * @author Lewis Perry ||| Nickname = Slippy
 *
 * @version 0.43
 *
 * */

public  class Main extends Application {






    public static void main(String[] args) {


        launch(args);
    }



    public  void start(Stage Main_Menu) throws IOException { //this is the start of the gui and login menu
        Stage Login = new Stage();


        FXMLLoader fxmlLoader = new FXMLLoader(User.class.getResource("Staff_Login.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 620, 640);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Login.setTitle("Staff Login");
        Login.setScene(scene);
        Login.show();

        //the actuall login and checker for account is in the controller class for the sake of keeping current track of the User account
    }



    }






