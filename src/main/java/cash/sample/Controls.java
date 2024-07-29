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



public User Current_User = new User("");
public long Start_Shift;

    


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
    public void Login(ActionEvent w){

//        Stage User_Login = (Stage) ((Node) w.getSource()).getScene().getWindow();
//
//        try (FileInputStream fileInputStream = new FileInputStream("Users.txt")) {
//            User_Login.close();
//
//
//            do {
//                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//
//                Current_User = (User) objectInputStream.readObject();
//                if (Current_User.getName().equals(Current_User.getName().toLowerCase())) {
//                     Start_Shift = System.currentTimeMillis();
//                     System.out.println(Current_User.getName());
//
//                   Current_User.employe_Start(Current_User);
//                    break;
//                }
//            } while (true);
//
//        } catch (IOException e) {
//            System.out.println("The account was not found");
//            Main.Menu();
//
//        } catch (ClassNotFoundException e) {
//            System.out.println("cash.sample.User Error 3: Class not found"); //if this fails than that means a Non cash.sample.User class has been found and selected
//            Main.Menu(); //returns cash.sample.User On error
//            throw new RuntimeException(e);
//
//        }




        String data_Name;
        String name = User_Name.getText();

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
                    Start_Shift = System.currentTimeMillis();
                     System.out.println(Current_User.getName());

                   Current_User.employe_Start(Current_User);
                    break;



                }else {
                    System.out.println("Account not found");
                    Main.Menu();
                }

            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public void Start_Sales(ActionEvent e) throws IOException {

        User.Start_Sales();




    }

@FXML
private TextField Sale_Value;
    public void Submit_Sales(ActionEvent w){
        Stage Sales = (Stage) ((Node) w.getSource()).getScene().getWindow(); //might not be needed tbd


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
