package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Swift Surfer");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        primaryStage.setScene(new Scene(root, screenSize.getWidth()/4, (screenSize.getHeight()/2)));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
