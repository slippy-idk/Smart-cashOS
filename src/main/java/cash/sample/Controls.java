package cash.sample;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Controls {



    


    public void Staff(ActionEvent e){

       Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();

       stage.close();





        User.Login();
    }
}
