package Models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Hospital extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.resizableProperty().setValue(Boolean.FALSE);

        FXMLLoader fxmlLoader = new FXMLLoader(Hospital.class.getResource("WelcomeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 680);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/icon.png")));
        stage.getIcons().add(image);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}