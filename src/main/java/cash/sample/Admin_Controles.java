package cash.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;

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
    private Text CreateAccountTitle;

    @FXML
    private TextField Password;
    @FXML
    private CheckBox Admin_Check;
    @FXML
    private TextField Pass_Check;

    public void Submit_create(ActionEvent w) throws IOException { //creates new account
//        User.Create_Account(Enter_Name.getText());

        try{




            Connection connection = DriverManager.getConnection( //creates connection with the sql databse
                    "jdbc:mysql://127.0.0.1:3306/test",
                    "root",
                    "1234"
            );

            //used to covert text boxes to string

            String name = Enter_Name.getText();
            String password = Password.getText();
            String User_Rights = "Staff";
            boolean Admin = Admin_Check.isSelected();
            String Passcheck = Pass_Check.getText();

            if(Admin){ //used to check if the password was entered multiple times
                User_Rights = "Admin";
            }

            if(! Passcheck.equals(password)){
                CreateAccountTitle.setText("Password Is incorrect");
            }else {


                String insert = "INSERT INTO USER  (name, password, Rights)" + //inserts the new account into the databse
                        "VALUES (?, ?, ?)";


                PreparedStatement preparedStatement = connection.prepareStatement(insert);
                preparedStatement.setString(1, name.toLowerCase());
                preparedStatement.setString(2, password.toLowerCase());
                preparedStatement.setString(3, User_Rights);




                preparedStatement.executeUpdate();
                preparedStatement.close();

                Stage Current = (Stage) ((Node) w.getSource()).getScene().getWindow();
                Current.close();

                Back2Menu();
            }



        }catch (SQLException e){
            System.out.println("Error");
        }


    }


    @FXML
    TextField Create_Item_ItemName;

    @FXML
    TextField Create_Item_ItemPrice;

    @FXML
    Text Create_Item_Error;

    public void Create_Item() throws IOException{ //used to create items in the item table


        try {


            String Item_Name = Create_Item_ItemName.getText();

            BigDecimal Item_Price = new BigDecimal(Create_Item_ItemPrice.getText());
            BigDecimal Item_Price_Rounder;

            Item_Price_Rounder = Item_Price.setScale(2, RoundingMode.FLOOR);  // this is used to round 


            System.out.println(Item_Price_Rounder);





        }catch (NumberFormatException q){
            Create_Item_Error.setText("Please enter a Number");

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


    public void CreateItem_Menu(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //gets the current window to back out

        stage.close();

        Stage CreateItem = new Stage();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Create_Item.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        CreateItem.setTitle("Creation Item");
        CreateItem.setScene(scene);
        CreateItem.show();



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
