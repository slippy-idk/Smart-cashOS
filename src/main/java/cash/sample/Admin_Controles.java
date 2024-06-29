package cash.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Admin_Controles {

@FXML
private TextField Enter_Name;



    public void Create_Account(ActionEvent e) throws IOException {


        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin_NameInsert.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();



    }


    public void Submit_create(ActionEvent w) throws IOException {
        User.Create_Account(Enter_Name.getText());


        Stage stage = (Stage) ((Node) w.getSource()).getScene().getWindow();
        stage.close();


        Back2Menu();
    }

    public void View_Account(ActionEvent e) throws IOException {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin_NameInsert2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    public void Submit_View(ActionEvent w) throws IOException {
        User.View_Account(Enter_Name.getText());


        Stage stage = (Stage) ((Node) w.getSource()).getScene().getWindow();
        stage.close();


        Back2Menu();
    }

    public void Backout(ActionEvent e) throws IOException {
        Main main = new Main();


        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        stage.close();



        Stage stage1 = new Stage();

        main.start(stage1);
    }


    public void Back2Menu() throws IOException {

        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin_Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


    }




}
