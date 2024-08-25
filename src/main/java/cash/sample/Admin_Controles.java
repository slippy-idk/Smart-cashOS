package cash.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Admin_Controles  {




    public void Create_Account(ActionEvent w ) throws IOException {


        Stage Current = (Stage) ((Node) w.getSource()).getScene().getWindow(); //for getting the main menu stage

        Current.close(); //closes the main menu stage


        Stage Stage_Account_Creation = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Create_Account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        Stage_Account_Creation.setTitle("Account Creation!");
        Stage_Account_Creation.setScene(scene);
        Stage_Account_Creation.show();









    }

//the below get a text field to get user input from the Create Account GUI
    @FXML
    private TextField Enter_Name;

    @FXML
    private TextField Password;
    @FXML
    private CheckBox Admin_Check;

    public void Submit_create(ActionEvent w) throws IOException { //creates new account
//        User.Create_Account(Enter_Name.getText());

        try{




            Connection connection = DriverManager.getConnection( //creates connection with the sql databse
                    "jdbc:mysql://127.0.0.1:3306/test",
                    "root",
                    "1234"
            );

            String name = Enter_Name.getText();
            String password = Password.getText();
            String User_Rights = "Staff";
            boolean Admin = Admin_Check.isSelected();

            if(Admin == true){
                User_Rights = "Admin";
            }



            String insert = "INSERT INTO USER  (name, password, Rights)"+
                    "VALUES (?, ?, ?)";


            PreparedStatement preparedStatement= connection.prepareStatement(insert);
            preparedStatement.setString(1, name.toLowerCase());
            preparedStatement.setString(2, password.toLowerCase());
            preparedStatement.setString(3, User_Rights);


            //tbd enter code for cheching password

            preparedStatement.executeUpdate();
            preparedStatement.close();

            Stage Current = (Stage) ((Node) w.getSource()).getScene().getWindow();
            Current.close();

            Back2Menu();



        }catch (SQLException e){
            System.out.println("Error");
        }


    }

    public void View_Account() throws IOException {
        Stage Stage_Admin_Login = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View_Account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        Stage_Admin_Login.setTitle("Hello!");
        Stage_Admin_Login.setScene(scene);
        Stage_Admin_Login.show();
    }


    public void Submit_View() { //submits the user request to view



        User user = new User("");
        String name = Enter_Name.getText();

        try {

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/test",
                    "root",
                    "1234"
            );


//            Stage User_Login = (Stage) ((Node) w.getSource()).getScene().getWindow();


            String sql = "SELECT * FROM user";
            Statement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery(sql);



            while (resultSet.next()){

                user.setName(resultSet.getString("name"));

                if(name.equals(user.getName())){
                    //tbd add in view account screen


                    break;



                }else {
                    System.out.println("Account not found");


                    //tbd put in account
                }

            }

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Backout(ActionEvent e) throws IOException { // this loads back to the login screen
        Main main = new Main();


        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow(); //gets the current window to back out

        stage.close();



        Stage Main_Menu = new Stage();

        main.start(Main_Menu);
    }


    public void Back2Menu() throws IOException { //returns the user back to menu

        Stage Stage_Admin_Menu = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin_Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        Stage_Admin_Menu.setTitle("Admin Menu");
        Stage_Admin_Menu.setScene(scene);
        Stage_Admin_Menu.show();


    }

    public void Admin_Start() throws IOException { //this is the start of the gui and login menu

        Stage Staff_Menu = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin_Main_Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        Staff_Menu.setTitle("Hello");
        Staff_Menu.setScene(scene);
        Staff_Menu.show();


    }



}
