package cash.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.*;

public class Admin_Controles {

    @FXML
    private TextField Enter_Name;


    public void Create_Account() throws IOException {


        Stage Stage_Account_Creation = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin_NameInsert.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        Stage_Account_Creation.setTitle("Account Creation!");
        Stage_Account_Creation.setScene(scene);
        Stage_Account_Creation.show();









    }


    public void Submit_create() throws IOException { //creates new account
//        User.Create_Account(Enter_Name.getText());

        try{


            Connection connection = DriverManager.getConnection( //creates connection with the sql databse
                    "jdbc:mysql://127.0.0.1:3306/test",
                    "root",
                    "1234"
            );

            String name = Enter_Name.getText();

            String insert = "INSERT INTO USER  (Name)"+
                    "VALUES (?)";


            PreparedStatement preparedStatement= connection.prepareStatement(insert);
            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            Back2Menu();



        }catch (SQLException e){
            System.out.println("Error");
        }


    }

    public void View_Account() throws IOException {
        Stage Stage_Admin_Login = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin_NameInsert2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        Stage_Admin_Login.setTitle("Hello!");
        Stage_Admin_Login.setScene(scene);
        Stage_Admin_Login.show();
    }


    public void Submit_View(ActionEvent w) { //submits the user request to view



        String data_Name;
        String name = Enter_Name.getText();

        try {

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/test",
                    "root",
                    "1234"
            );


            Stage User_Login = (Stage) ((Node) w.getSource()).getScene().getWindow();


            String sql = "SELECT * FROM user";
            Statement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery(sql);



            while (resultSet.next()){
                data_Name = resultSet.getString("Name");

                if(name.equals(data_Name)){
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




}
