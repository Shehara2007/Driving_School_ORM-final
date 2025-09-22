package lk.ijse.driving_school_orm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Appinitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Appinitializer.class.getResource("/lk/ijse/driving_school_orm/Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Driving School Management Project");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
