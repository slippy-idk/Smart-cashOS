package cash.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controls {



public static User Current_User = new User(""); //this is used to track the current users account
public long Start_Shift; //gets the user current starting time as of shift for u8se



    public void Admin(ActionEvent e){ //used to load the admin menu
        Stage Admin_Menu = (Stage) ((Node) e.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(User.class.getResource("Admin_Menu.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 620, 640);
        } catch (IOException w) {
            throw new RuntimeException(w);
        }
        Admin_Menu.setTitle("Admin Menu");
        Admin_Menu.setScene(scene);
        Admin_Menu.show();

    }

//the fxml before are to accesss the boxex in the login  screen
    @FXML
    private TextField User_Name;
    @FXML
    private TextField Password;

    //used to display that the account was not found
    @FXML
    private Text LoginTitle;


    public void Login_Submit(ActionEvent w){ //used when the user attempts a login and than handles the login process

        String data_Name; //for checking the current database name with the user entered
        String name = User_Name.getText(); // takes the name the user entered
        String password = Password.getText();
        String data_Pass;

        //used to add the stats to the current user object so that the users stats arnt overidden

        double data_Hours;
        double data_Revenue;
        String data_Rights;

        try {

            Connection connection = DriverManager.getConnection( //creates connection with the sql databse
                    "jdbc:mysql://127.0.0.1:3306/test",
                    "root",
                    "1234"
            );


            Stage User_Login = (Stage) ((Node) w.getSource()).getScene().getWindow();


            String sql = "SELECT * FROM user"; //used to get the database
            Statement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery(sql);


            //tbd rework this for the love of god
            boolean accountFound = false;



            while (resultSet.next()){
                data_Name = resultSet.getString("name");
                data_Pass = resultSet.getString("password");
                data_Revenue = resultSet.getDouble("Revenue");
                data_Hours = resultSet.getDouble("HoursWorked");
                data_Rights = resultSet.getString("Rights");


                if(name.equals(data_Name.toLowerCase()) && password.equals(data_Pass.toLowerCase())){
                    Start_Shift = System.currentTimeMillis();


                     Current_User.setName(data_Name.toLowerCase());

                     Current_User.setHoursWorked((long) data_Hours);
                     Current_User.setGrossEarning((long) data_Revenue);
                     Current_User.setUser_Rights(data_Rights);

                    accountFound = true;


                     if (Current_User.getUser_Rights().equals("Admin")){
                         Admin_Start();
                         User_Login.close();

                     }else{



                   employe_Start();


                   User_Login.close();



                }
                }
            }if (!accountFound) {

                LoginTitle.setText("Account not Found");
            }






        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


    } //when the user begins to login this handles the login


    public void Start_Sales() throws IOException { //used to put the user into the sales screen

        Stage Sales_Tracker = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(User.class.getResource("Item_Sales.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        Sales_Tracker.setTitle("Hello");
        Sales_Tracker.setScene(scene);
        Sales_Tracker.show();

    }

    ArrayList<String> items = new ArrayList<>(); //global tbd
    ArrayList<Double> price = new ArrayList<>(); //global tbd
@FXML
    private TextField ItemAdd;

@FXML
private Text ItemSaleError;
    public void ItemSales() throws SQLException {
        String Current_Item;
        Current_Item = ItemAdd.getText().toLowerCase();

        String sql = "SELECT * FROM items ";

        Connection connection = DriverManager.getConnection( //creates connection with the sql databse
                "jdbc:mysql://127.0.0.1:3306/test",
                "root",
                "1234"
        );

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery(sql);

        boolean ItemFound= false;

        while (resultSet.next()){
            String data_Item = resultSet.getString("Name");
            double data_Price = Double.parseDouble(resultSet.getString("Price"));




            if(Current_Item.equals(data_Item.toLowerCase())){
                items.add(data_Item);
                price.add(data_Price);
                ItemSaleError.setText("Added Item");
                ItemFound = true;

            }
        }
        if (!ItemFound){
            ItemSaleError.setText("The Item has not been found");
        }


    }


    public void ItemSaleEnd(ActionEvent e) throws SQLException {
        String CurrentItem;
        double SaleWorth = 0;

        Connection connection = DriverManager.getConnection( //creates connection with the sql databse
                "jdbc:mysql://127.0.0.1:3306/test",
                "root",
                "1234"
        );

        for(int i = 0;  i < items.size(); i++){
            CurrentItem = items.get(i);

            String Updatesql = "Update items SET Sales_Ammount= Sales_Ammount + 1,Quantity = Quantity - 1 WHERE Name=?";


            PreparedStatement preparedStatement = connection.prepareStatement(Updatesql);

            preparedStatement.setString(1, CurrentItem);

            preparedStatement.executeUpdate();

            SaleWorth = SaleWorth + price.get(i);

        }
connection.close();

        Current_User.setGrossEarning(Current_User.getGrossEarning() + SaleWorth);


        ItemSaleError.setText("Sale Succeded");

        Sale_Tracker();



    }

    public void Sale_Tracker() throws SQLException { //used to get the date of the sales and add them to the sales database

        Connection connection = DriverManager.getConnection( //creates connection with the sql databse
                "jdbc:mysql://127.0.0.1:3306/test",
                "root",
                "1234"
        );

        String sql = "INSERT into Sales (Name,Date,Time) "+
                "VALUES (?,?,?)";


        //used to create a date format and get the dates only  used here so no need for global

        DateTimeFormatter Time = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd:MM:yyyy");

        for (String item : items) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, item);
            preparedStatement.setString(2, String.format(LocalDate.now().format(date)));
            preparedStatement.setString(3, String.format(LocalTime.now().format(Time)));

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }


    }










@FXML
private TextField Sale_Value;
    public void Submit_Sales(){ //this is used when a user sumbits a sale in here to make it repitible

        System.out.println(Current_User.getGrossEarning());


        double Sale = Double.parseDouble(Sale_Value.getText());

        Current_User.setGrossEarning(Sale+Current_User.getGrossEarning());

        System.out.println(Current_User.getGrossEarning());
        System.out.println(Current_User.getName());







    }

    public void End_Sales(ActionEvent w) { //used to end the sale and modify the users stats


        //code below is for storing the users stats into databse

        long End_Shift = System.currentTimeMillis();

        long Final_Time = End_Shift - Start_Shift;

        Final_Time = Final_Time / 1000;
        Final_Time = Final_Time/60;
        Final_Time = Final_Time /60;

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
            System.out.println("SQL Error");


        }

    }

    public void employe_Start() throws IOException { //this is the start of the gui and login menu

        Stage Staff_Menu = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Staff_Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        Staff_Menu.setTitle("Hello");
        Staff_Menu.setScene(scene);
        Staff_Menu.show();
    }

    public void Admin_Start( ) throws IOException { //this is the start of the gui and login menu

        Stage Staff_Menu = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin_Main_Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        Staff_Menu.setTitle("Hello");
        Staff_Menu.setScene(scene);
        Staff_Menu.show();
    }



    public void Logout(ActionEvent e){ //lets the user logout

        Stage Main_Menu = (Stage) ((Node) e.getSource()).getScene().getWindow();

        Main_Menu.close();




    }


    public void Self_View_Submit(){ //allows the user to view their own account doesnt use the databse as we have their stats in current user

        Stage Self_view = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(User.class.getResource("Self_View_account.fxml"));
        Scene scene;
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


    public void Update_Self(){ //used to update the self view screen due to the way labels are loaded this is needed

        User_Hours.setText(Long.toString(Current_User.getHoursWorked()));


        User_Revenue.setText(Double.toString(Current_User.getGrossEarning()));




    }
}
