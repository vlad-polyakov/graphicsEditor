package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = new MainWindow().createScene();
        primaryStage.setTitle("Graphics Editor");
        primaryStage.setScene(new Scene(root, 900, 900));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
