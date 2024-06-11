import java.util.Scanner;

/**
 * @author Lewis Perry Nickname = Slippy
 *
 * @version 0.01
 *
 * */

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner User_Input = new Scanner(System.in);
    static String input;
    //these two global variables are designed for user input

    public static void main(String[] args) {


Menu();




    }

    public static void Menu(){

        System.out.println("What user would you like to login as");
        System.out.println("1: Employee");
        System.out.println("2: Administrator");
        String input = User_Input.nextLine();



        switch (input){
            case "1":
                User.Login();



            case "2":
                Administrator();

            default:
                System.out.println("You entered A wrong input try again");
                Menu();
        }

    }


    public static void Administrator(){

        System.out.println("What would you like to do");
        System.out.println("1: Create An employee Account");
        System.out.println("2: View An employee Account");
        System.out.println("3: Back out");

        input = User_Input.nextLine();

        switch (input){
            case "1":
                User.Create_Account();

            case "2":
                User.View_Account();

            case "3":
                Menu();

            default:
                System.out.println("You entered an incorrect Input try again");
                Administrator();
        }

    }

//    public void start(Stage stage) throws IOException { //this is the start of the gui and login menu
//        Group group = new Group();
//        Scene scene = new Scene(group, 400, 400);
//        stage.setTitle("Login");
//
//
//        //grid
//        StackPane stackPane = new StackPane();
//
//
//
//
//
//
//        //Defining the UserName
//        TextField UserName = new TextField();
//        UserName.setPromptText("Name: ");
//        UserName.getText();
//        UserName.setPrefColumnCount(10);
//
//        group.getChildren().add(UserName);
//
//
//
//
//
//
//
//
//
//        stage.setScene(scene);
//        stage.show();
//
//
//
//
//
//
//
//
//
//
//
//    }


}