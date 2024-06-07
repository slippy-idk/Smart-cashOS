import javax.naming.Name;
import java.io.*;
import java.security.PublicKey;

public class User implements Serializable {

    private String Name;

    private int HoursWorked;

    private  int GrossEarning;

    public String getName() {
        return Name;
    }

    public int getHoursWorked() {
        return HoursWorked;
    }

    public int getGrossEarning() {
        return GrossEarning;
    }

    public User(String name) {
        Name = name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setHoursWorked(int hoursWorked) {
        HoursWorked = hoursWorked;
    }

    public void setGrossEarning(int grossEarning) {
        GrossEarning = grossEarning;
    }



    public static void Create_Account()  { //used to create a user account
        try (FileOutputStream fileOutputStream = new FileOutputStream("Users.txt", true)) {
            ObjectOutputStream objectOutputStream =  new ObjectOutputStream(fileOutputStream);


            System.out.println("Please Enter the name of the employee");
            Main.input = Main.User_Input.nextLine();
            User Temp_User = new User(Main.input.toLowerCase());


            objectOutputStream.writeObject(Temp_User);
            System.out.println("Successfully Created New Employee Account");
        } catch (IOException e) {
            System.out.println("User Error 1: failed to save User IO or Runtime error Occurred");
            Main.Administrator();//returns user on Error
            throw new RuntimeException(e);
        }

        Main.Administrator();//returns User without risk of triggering Other Functions
    }




    public static void View_Account(){
        String name;

        System.out.println("Please enter the name of the Employee your trying to view");
        name = Main.User_Input.nextLine();



        try(FileInputStream fileInputStream =new FileInputStream("Users.txt")){


            do {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                User Temp_User = (User) objectInputStream.readObject();
                if (Temp_User.Name.equals(name.toLowerCase())) {
                    System.out.println(Temp_User);
                    Main.Administrator();
                }
            } while (true);

        } catch (IOException e) {
            System.out.println("The account was not found");
            Main.Administrator(); //returns User On error
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("User Error 3: Class not found"); //if this fails than that means a Non User class has been found and selected
            Main.Administrator(); //returns User On error
            throw new RuntimeException(e);

        }



    }

    public static void Login (){
        System.out.println("Please enter your name");
        Main.t

    }

}
