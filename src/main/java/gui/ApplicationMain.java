package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class that starts our application.
 *
 * @author mjo
 */
public class ApplicationMain extends Application {

    /**
     * Main method
     *
     * @param args unused
     */
    public static void main(String... args) {
        launch(args);
    }

    /**
     * Creating the stage and showing it. This is where the initial size and the
     * title of the window are set.
     *
     * @param stage the stage to be shown
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("UserInterface.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1150, 750);
        //stage.setResizable(false);
        stage.setTitle("Hase und Igel");
        stage.sizeToScene();
        stage.setScene(scene);
        stage.getIcons().add(new Image("169.jpg"));
        stage.show();
    }
}
