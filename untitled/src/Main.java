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


Login();




    }

    public static void Login(){

        System.out.println("What user would you like to login as");
        System.out.println("1: Employee");
        System.out.println("2: Administrator");
        String input = User_Input.nextLine();



        switch (input){
            case "1":
                System.out.println("Employee"); //tbd
                break;

            case "2":
                Administrator();

            default:
                System.out.println("You entered A wrong input try again");
                Login();
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
                Login();

            default:
                System.out.println("You entered an incorrect Input try again");
                Administrator();
        }

    }



}