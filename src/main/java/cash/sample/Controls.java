package cash.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Controls {



    


    public void Staff(ActionEvent e){

       Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();

       stage.close();





        User.Login();
    }


@FXML
private TextField User_Name;
    public void Login(ActionEvent w){

        Stage stage = (Stage) ((Node) w.getSource()).getScene().getWindow();

        System.out.println(User_Name.getText());


        User.login_Check(User_Name.getText());








    }
}
