import java.io.*;

public class User implements Serializable {

    private String Name;

    private long HoursWorked;

    private  int GrossEarning;

    public String getName() {
        return Name;
    }

    public long getHoursWorked() {
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

    public void setHoursWorked(long hoursWorked) {
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

        System.out.println("Please enter the name of the Employee your trying to view");
        String name = Main.User_Input.nextLine();



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

    public static void Login (){ //why is this given me an error when is in use tbd
        System.out.println("Please enter your name");
        String name = Main.User_Input.nextLine();


        try(FileInputStream fileInputStream =new FileInputStream("Users.txt")){


            do {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                User Temp_User = (User) objectInputStream.readObject();
                if (Temp_User.Name.equals(name.toLowerCase())) {
                    Temp_User.employee_Start(Temp_User);
                }
            } while (true);

        } catch (IOException e) {
            System.out.println("The account was not found");
            Main.Menu(); //returns User On error
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("User Error 3: Class not found"); //if this fails than that means a Non User class has been found and selected
            Main.Menu(); //returns User On error
            throw new RuntimeException(e);

        }



    }


    public  void employee_Start(User args){

        long Start_Shift = System.currentTimeMillis(); //this variable is to keep track of the current time for working out hours worked


        System.out.println("What would you like to do");
        System.out.println("END: end your shift on console");

        Main.input = Main.User_Input.nextLine();


        switch (Main.input.toLowerCase()){
            case "end":
                End_shift(args, Start_Shift);



        }

    }


    public  void End_shift(User User, long Start_Shift){
        long End_Shift = System.currentTimeMillis();


        End_Shift = End_Shift - Start_Shift;

        long Hours = End_Shift/1000;
        Hours = Hours/60;
        Hours = Hours/60;

        User.setHoursWorked(Hours);

        System.out.println("You have worked this amount of Hours");
        System.out.println(User.getHoursWorked());


        System.out.println(" "); // used to space menu and the end shift screen
        Main.Menu();//returns the User to the Menu to not trigger an Io error






    }

}
