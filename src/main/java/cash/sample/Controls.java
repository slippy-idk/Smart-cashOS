package cash.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Controls {



public User Current_User = new User("");
public long Start_Shift;

    


    public void Staff(ActionEvent e){

       Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();

       stage.close();





        User.Login();
    }

    public void Admin(ActionEvent e){
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(User.class.getResource("Admin_Menu.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 620, 640);
        } catch (IOException w) {
            throw new RuntimeException(w);
        }
        stage.setTitle("Admin Menu");
        stage.setScene(scene);
        stage.show();

    }


@FXML
private TextField User_Name;
    public void Login(ActionEvent w){

        Stage stage = (Stage) ((Node) w.getSource()).getScene().getWindow();

        try (FileInputStream fileInputStream = new FileInputStream("Users.txt")) {
            stage.close();


            do {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                Current_User = (User) objectInputStream.readObject();
                if (Current_User.getName().equals(Current_User.getName().toLowerCase())) {
                     Start_Shift = System.currentTimeMillis();
                     System.out.println(Current_User.getName());

                   Current_User.employe_Start(Current_User);
                    break;
                }
            } while (true);

        } catch (IOException e) {
            System.out.println("The account was not found");
            Main.Menu();

        } catch (ClassNotFoundException e) {
            System.out.println("cash.sample.User Error 3: Class not found"); //if this fails than that means a Non cash.sample.User class has been found and selected
            Main.Menu(); //returns cash.sample.User On error
            throw new RuntimeException(e);

        }


    }


    public void Start_Sales(ActionEvent e) throws IOException {

        User.Start_Sales();




    }

@FXML
private TextField Sale_Value;
    public void Submit_Sales(ActionEvent w){
        Stage stage = (Stage) ((Node) w.getSource()).getScene().getWindow();


        double Sale = Double.parseDouble(Sale_Value.getText());

        Current_User.setGrossEarning(Sale+Current_User.getGrossEarning());

        System.out.println(Current_User.getGrossEarning());







    }

    public void End_Sales(ActionEvent w) throws IOException {

        Current_User.employe_Start(Current_User);


    }


    public void Logout(ActionEvent e){

        Current_User.End_shift(Current_User, Start_Shift);

    }
}
