package cash.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.sql.*;

public class Controls {



public static User Current_User = new User(""); //this is used to track the current users account
public long Start_Shift; //gets the user current starting time as of shift for u8se

    public String UserType = "Staff";



    


    public void Staff(ActionEvent e){

       Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();

       stage.close();





        Login();
    }


    public static void Login() { //load the login screen
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

        //used to add the stats to the current user

        double data_Hours;
        double data_Revenue;

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
                data_Revenue = resultSet.getDouble("Revenue");
                data_Hours = resultSet.getDouble("HoursWorked");


                if(name.equals(data_Name.toLowerCase()) && password.equals(data_Pass.toLowerCase())){
                    Start_Shift = System.currentTimeMillis();



                    System.out.println(Current_User.getName());

                     Current_User.setName(data_Name.toLowerCase());

                     Current_User.setHoursWorked((long) data_Hours);
                     Current_User.setGrossEarning((long) data_Revenue);



                   employe_Start(Current_User);

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

        Sales(); //load the start sales for the user

    }

    public static void Sales() throws IOException { //loads the start of sales screen the sales screen is handled in control tab

        Stage Sales_Tracker = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(User.class.getResource("Sales.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        Sales_Tracker.setTitle("Hello");
        Sales_Tracker.setScene(scene);
        Sales_Tracker.show();
    }




@FXML
private TextField Sale_Value;
    public void Submit_Sales(ActionEvent w){ //this is used when a user sumbits a sale in here to make it repitible
        Stage Sales = (Stage) ((Node) w.getSource()).getScene().getWindow(); //might not be needed tbd
        System.out.println(Current_User.getGrossEarning());


        double Sale = Double.parseDouble(Sale_Value.getText());

        Current_User.setGrossEarning(Sale+Current_User.getGrossEarning());

        System.out.println(Current_User.getGrossEarning());
        System.out.println(Current_User.getName());







    }

    public void End_Sales(ActionEvent w) throws IOException {

//        System.out.println(Current_User.getGrossEarning()); //tbd debug

        //code below is for storing the users stats into databse

        double End_Sales = Current_User.getGrossEarning();
        long End_Shift = System.currentTimeMillis();

        long Final_Time = End_Shift - Start_Shift;

        Final_Time = Final_Time / 60000;
        Final_Time = Final_Time/60;
        Final_Time = Final_Time /60; //tbd add this to track hours

        Current_User.setHoursWorked(Current_User.getHoursWorked() + Final_Time);


        try {

            Connection connection = DriverManager.getConnection( //creates connection with the sql databse
                    "jdbc:mysql://127.0.0.1:3306/test",
                    "root",
                    "1234"
            );


//updates the user info


            String SQL = "UPDATE user SET Revenue=?, HoursWorked=? WHERE name=?";

            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setDouble(1, Current_User.getGrossEarning());
            statement.setDouble(2, Current_User.getHoursWorked());
            statement.setString(3,Current_User.getName());

            statement.executeUpdate();

            connection.close();


            Stage Sales = (Stage) ((Node) w.getSource()).getScene().getWindow();


            Sales.close();
















        }catch (SQLException e){


        }

    }

    public void employe_Start(User user) throws IOException { //this is the start of the gui and login menu

        Stage Staff_Menu = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Staff_Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        Staff_Menu.setTitle("Hello");
        Staff_Menu.setScene(scene);
        Staff_Menu.show();


    }

    public void Logout(ActionEvent e){

        Current_User.End_shift(Current_User, Start_Shift);

        //shows the user the end of shift

    }


    public void Self_View_Submit(ActionEvent e){

        Stage Self_view = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(User.class.getResource("Self_View_account.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 620, 640);
        } catch (IOException w) {
            throw new RuntimeException(w);
        }
        Self_view.setTitle("View Account");
        Self_view.setScene(scene);
        Self_view.show();







    }



    @FXML
    private Label User_Hours;

    @FXML
    private Text User_Revenue;

    @FXML
    private Text User_Type;
    public void Update_Self(ActionEvent e){

        User_Hours.setText(Long.toString(Current_User.getHoursWorked()));
        User_Type.setText(UserType);

        User_Revenue.setText(Double.toString(Current_User.getGrossEarning()));




    }
}
