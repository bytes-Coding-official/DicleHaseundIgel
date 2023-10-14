package gui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.Color;
import logic.Figure;
import logic.Game;
import logic.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Main class for the user interface.
 *
 * @author mjo, cei
 */
public class UserInterfaceController implements Initializable {

    public Spinner<Integer> countPlayer;
    public Pane window;
    public VBox nameFields;
    public Button closeGame;
    public Button startButton;
    public ImageView spielfeld;
    public Pane controlPanel;
    public VBox controlField;
    public VBox playersSpinner;
    public BorderPane borderPane;

    @FXML
    private ColorAdjust colorAdjust;

    private boolean isColorful = false;
    static final ArrayList<Player> players = new ArrayList<>();
    static final ArrayList<Figure> figures = new ArrayList<>();

    private void startButtonEvent() {
        Timeline timeline = new Timeline();

        // Determine the target saturation based on the current state
        double targetSaturation = isColorful ? -1.0 : 0.0;

        // Define keyframes for the animation
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(3),
                new KeyValue(colorAdjust.saturationProperty(), targetSaturation)
        );

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(1); // Run the animation once

        // Play the animation
        timeline.play();

        // Toggle the colorful state
        isColorful = !isColorful;


        int x = 344;
        int y = 344;
        int countOfPlayers = countPlayer.getValue();
        for (int i = 0; i < countOfPlayers; i++) {
            ImageView gameToken = new ImageView("Stein blau.png");
            gameToken.toFront();
            gameToken.setX(x += 20);
            gameToken.setY(y += 20);
            gameToken.setFitHeight(50);
            gameToken.setFitWidth(50);
            gameToken.idProperty().setValue("gamer" + i);
            gameToken.fitWidthProperty().bind(spielfeld.fitWidthProperty().divide(1000).multiply(60));
            window.getChildren().add(gameToken);
        }
        players.clear();
        players.add(new Player(((TextField) nameFields.getChildren().get(0)).getText()));
        players.add(new Player(((TextField) nameFields.getChildren().get(1)).getText()));

        if (countOfPlayers >= 3)
            players.add(new Player(((TextField) nameFields.getChildren().get(2)).getText()));
        if (countOfPlayers >= 4)
            players.add(new Player(((TextField) nameFields.getChildren().get(3)).getText()));
        if (countOfPlayers >= 5)
            players.add(new Player(((TextField) nameFields.getChildren().get(4)).getText()));
        if (countOfPlayers >= 6)
            players.add(new Player(((TextField) nameFields.getChildren().get(5)).getText()));

        Game.getInstance().getPlayers().addAll(players);
        addFigures();
        Game.getInstance().distributeCards();


    }

    private void addFigures() {

        new Figure(Game.getInstance().getFields().stream().filter(field -> field.getID() == 0).findFirst().get(), Color.BLUE, players.get(0));
        new Figure(Game.getInstance().getFields().stream().filter(field -> field.getID() == 0).findFirst().get(), Color.YELLOW, players.get(1));
        if (players.size() == 3)
            new Figure(Game.getInstance().getFields().stream().filter(field -> field.getID() == 0).findFirst().get(), Color.GREEN, players.get(2));
        if (players.size() == 4)
            new Figure(Game.getInstance().getFields().stream().filter(field -> field.getID() == 0).findFirst().get(), Color.ORANGE, players.get(3));
        if (players.size() == 5)
            new Figure(Game.getInstance().getFields().stream().filter(field -> field.getID() == 0).findFirst().get(), Color.RED, players.get(4));
        if (players.size() == 6)
            new Figure(Game.getInstance().getFields().stream().filter(field -> field.getID() == 0).findFirst().get(), Color.WHITE, players.get(5));
        Game.getInstance().setCurrentPlayer(players.get(0));
    }

    /**
     * This is where you need to add code that should happen during
     * initialization and then change the java doc comment.
     *
     * @param location  probably not used
     * @param resources probably not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        spielfeld.fitWidthProperty().bind(window.widthProperty());
        spielfeld.fitHeightProperty().bind(window.heightProperty());

        // Färbe das Hintergrundbild schwarz-weiß
        window.setEffect(colorAdjust);

        window.toBack();

        // Füge den Mausklick-Handler zum Farbwechsel hinzu
        startButton.setOnMouseClicked(event -> {
            // Create a timeline for the color adjustment animation
            startButtonEvent();
        });

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 6);

        valueFactory.setValue(4);

        countPlayer.setValueFactory(valueFactory);
    }


    @FXML
    protected void onSpinnerClick() {
        int countOfTextFields = nameFields.getChildren().size() - 1;
        int countOfPlayers = countPlayer.getValue();

        if (countOfPlayers > countOfTextFields) {
            for (int i = 0; i < countOfPlayers - countOfTextFields; i++) {
                TextField textField = new TextField();
                textField.setId("nameField" + i);
                textField.setMaxWidth(98);
                textField.promptTextProperty().setValue("Player" + (countOfPlayers + i));
                textField.setAlignment(Pos.valueOf("CENTER"));
                nameFields.getChildren().add(textField);
            }
        } else {
            for (int i = 0; i < countOfTextFields - countOfPlayers; i++) {
                nameFields.getChildren().remove(nameFields.getChildren().size() - 1);
            }
        }
    }

    public void createCloseGameWindow(MouseEvent mouseEvent) {
        // Erstelle ein Alert-Fenster, um den Benutzer zu fragen, ob er das Spiel schließen möchte.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Spiel schließen");
        alert.setHeaderText("Bitte bestätigen.");
        alert.setContentText("Möchten Sie das Spiel wirklich schließen?");

        // Zeige das Alert-Fenster und warte auf die Benutzeraktion.
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        // Überprüfe die Benutzeraktion.
        if (result == ButtonType.OK) {
            // Schließe das Spiel-Fenster.
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }
}
