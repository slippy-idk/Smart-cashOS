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
import java.sql.*;

public class Controls {



public User Current_User = new User(""); //this is used to track the current users account
public long Start_Shift; //gets the user current starting time as of shift for u8se

    


    public void Staff(ActionEvent e){

       Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();

       stage.close();





        User.Login();
    }

    public void Admin(ActionEvent e){
        Stage Admin_Menu = (Stage) ((Node) e.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(User.class.getResource("Admin_Menu.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 620, 640);
        } catch (IOException w) {
            throw new RuntimeException(w);
        }
        Admin_Menu.setTitle("Admin Menu");
        Admin_Menu.setScene(scene);
        Admin_Menu.show();

    }


@FXML
private TextField User_Name;

    @FXML
    private TextField Password;
    public void Login(ActionEvent w){

        String data_Name; //for checking the current database name with the user entered
        String name = User_Name.getText(); // takes the name the user entered
        String password = Password.getText();
        String data_Pass;

        try {

            Connection connection = DriverManager.getConnection( //creates connection with the sql databse
                    "jdbc:mysql://127.0.0.1:3306/test",
                    "root",
                    "1234"
            );


            Stage User_Login = (Stage) ((Node) w.getSource()).getScene().getWindow();


            String sql = "SELECT * FROM user";
            Statement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery(sql);


            //tbd rework this for the love of god
            boolean accountFound = false;



            while (resultSet.next()){
                data_Name = resultSet.getString("name");
                data_Pass = resultSet.getString("password");

                if(name.equals(data_Name.toLowerCase()) && password.equals(data_Pass.toLowerCase())){
                    Start_Shift = System.currentTimeMillis();
                     System.out.println(Current_User.getName());

                   Current_User.employe_Start(Current_User);

                   accountFound = true;
                   User_Login.close();



                }
            }if (!accountFound) {
                System.out.println("Account not found");
                // tbd put in account menu
            }






        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public void Start_Sales(ActionEvent e) throws IOException {

        User.Start_Sales(); //load the start sales for the user

    }

@FXML
private TextField Sale_Value;
    public void Submit_Sales(ActionEvent w){ //this is used when a user sumbits a sale in here to make it repitible
        Stage Sales = (Stage) ((Node) w.getSource()).getScene().getWindow(); //might not be needed tbd


        double Sale = Double.parseDouble(Sale_Value.getText());

        Current_User.setGrossEarning(Sale+Current_User.getGrossEarning());

        System.out.println(Current_User.getGrossEarning());







    }

    public void End_Sales(ActionEvent w) throws IOException {

        Current_User.employe_Start(Current_User);

        //tbd put user end screen

        //throws the user back to employee menu


    }


    public void Logout(ActionEvent e){

        Current_User.End_shift(Current_User, Start_Shift);

        //shows the user the end of shift

    }


    public void Self_View_Submit(ActionEvent e){

        Stage Self_view = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(User.class.getResource("Admin_Menu.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 620, 640);
        } catch (IOException w) {
            throw new RuntimeException(w);
        }
        Self_view.setTitle("Admin Menu");
        Self_view.setScene(scene);
        Self_view.show();




    }
}
